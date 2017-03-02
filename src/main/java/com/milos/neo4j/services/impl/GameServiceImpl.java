package com.milos.neo4j.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.GameConverter;
import com.milos.neo4j.converter.UserConverter;
import com.milos.neo4j.converter.UserGameConverter;
import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.data.UserGameData;
import com.milos.neo4j.domain.nodes.Game;
import com.milos.neo4j.domain.nodes.User;
import com.milos.neo4j.domain.nodes.UserGameScores;
import com.milos.neo4j.domain.relations.GameRelation;
import com.milos.neo4j.repository.GameRepository;
import com.milos.neo4j.repository.UserGameScoresRepository;
import com.milos.neo4j.repository.UserRepository;
import com.milos.neo4j.repository.relations.GameRelationRepository;
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.PlayService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameRelationRepository gameRelationRepository;

    @Autowired
    UserGameScoresRepository userGameScoresRepository;

    @Autowired
    GameConverter gameConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    PlayService playService;

    @Autowired
    UserGameConverter userGameConverter;

    @Transactional(readOnly = true)
    @Override
    public GameData getGameById(Long id) {
        GameData gameData = new GameData();
        gameConverter.copyFromEntityToData(gameRepository.findOne(id), gameData);
        return gameData;
    }

    @Transactional(readOnly = false)
    @Override
    public GameData createNewGame(UserData userData) {
        User user = userRepository.findOne(userData.getId());
        GameRelation gameRelation = gameRelationRepository.findGameRelationForUser(userData.getUsername());
        GameData gameData = null;
        if (user != null && gameRelation == null) {
            Game game = new Game();
            game.setFirstLetter(playService.choseLetter());
            Set<User> players = new HashSet<>();
            players.add(user);
            game.setPlayers(players);
            game.setNumberOfPlayers(Long.valueOf(players.size()));
            game.setActiveGame(false);
            gameRepository.save(game);
            gameData = new GameData();
            gameConverter.copyFromEntityToData(game, gameData);
        }
        return gameData;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<GameData> getAllInactiveGames() {
        Set<Game> inactiveGames = gameRepository.findAllInactiveGames(false);
        Set<GameData> inactiveGameDatas = new HashSet<>();
        gameConverter.copyFromEntitySetToDataSet(inactiveGames, inactiveGameDatas, new GameData());
        return inactiveGameDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public GameData addPlayer(UserData userData, Long gameId) {
        Game game = gameRepository.findOne(gameId);
        User user = new User();
        userConverter.copyFromDataToEntity(userData, user);
        Set<User> players = game.getPlayers();
        boolean isInSet = false;
        for (User player : players) {
            if (player.getUsername().equals(userData.getUsername())) {
                isInSet = true;
            }
        }
        if (!isInSet) {
            players.add(user);
            game.setPlayers(players);
            game.setNumberOfPlayers(Long.valueOf(game.getPlayers().size()));
            game = gameRepository.save(game);
        }
        GameData gameData = new GameData();
        gameConverter.copyFromEntityToData(game, gameData);
        return gameData;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<UserGameData> getAllGamesForPlayer(UserData userData) {
        Set<UserGameData> userGameDatas = new HashSet<>();
        if (userData != null) {
            Set<Game> games = gameRelationRepository.findGamesForUser(userData.getUsername());
            if (!games.isEmpty()) {
                for (Game game : games) {
                    UserGameScores userGameScores = userGameScoresRepository.getUserGameScore(userData.getUsername(),
                            game.getId());
                    if (userGameScores != null) {
                        UserGameData userGameData = new UserGameData();
                        userGameConverter.copyFromEntityToData(userGameScores, userGameData);
                        userGameDatas.add(userGameData);
                    }
                }
            }
        }
        return userGameDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public void createNewUserGame(UserData userData, GameData game) {
        UserGameScores gameScores = new UserGameScores();
        gameScores.setGameId(game.getId());
        gameScores.setScore(userData.getGameScore());
        gameScores.setUsername(userData.getUsername());
        userGameScoresRepository.save(gameScores);
    }

    @Transactional(readOnly = true)
    @Override
    public UserGameData getUserGameData(String username, Long gameId) {
        UserGameScores gameScores = userGameScoresRepository.getUserGameScore(username, gameId);
        if (gameScores != null) {
            UserGameData userGameData = new UserGameData();
            userGameConverter.copyFromEntityToData(gameScores, userGameData);
            return userGameData;
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUserGame(String username, Long gameId, Long score) {
        userGameScoresRepository.updateUserGameScore(username, gameId, score);
    }

    @Transactional(readOnly = false)
    @Override
    public void updateGameLetter(String letter, Long gameId) {
        gameRepository.updateGameLetter(letter, gameId);
    }

}
