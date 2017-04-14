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
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class GameServiceImpl implements GameService {

    private static Logger GEOGRAPHY_LOGGER = LoggerFactory.getLogger("GEOGRAPHY");

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
        try {
            gameConverter.copyFromEntityToData(gameRepository.findOne(id), gameData);
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("getGameById throws error.", ex);
            throw ex;
        }
        return gameData;
    }

    @Transactional(readOnly = false)
    @Override
    public GameData createNewGame(UserData userData) {
        GameData gameData = null;
        try {
            User user = userRepository.findOne(userData.getId());
            GameRelation gameRelation = gameRelationRepository.findGameRelationForUser(userData.getUsername());
            if (user != null && gameRelation == null) {
                Game game = new Game();
                game.setFirstLetter(playService.choseLetter());
                Set<User> players = new HashSet<>();
                players.add(user);
                game.setPlayers(players);
                game.setNumberOfPlayers(Long.valueOf(players.size()));
                game.setActiveGame(false);
                Date creationDate = new Date();
                game.setCreationDate(creationDate.getTime());
                game.setLocked(Boolean.FALSE);
                gameRepository.save(game);
                gameData = new GameData();
                gameConverter.copyFromEntityToData(game, gameData);
            }
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("createNewGame throws error.", ex);
            throw ex;
        }
        return gameData;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<GameData> getAllInactiveGames() {
        Set<GameData> inactiveGameDatas = new HashSet<>();
        try {
            Set<Game> inactiveGames = gameRepository.findAllInactiveGames(false);
            gameConverter.copyFromEntitySetToDataSet(inactiveGames, inactiveGameDatas, new GameData());
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("getAllInactiveGames throws error.", ex);
            throw ex;
        }
        return inactiveGameDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public GameData addPlayer(UserData userData, Long gameId) {
        User user = new User();
        GameData gameData = new GameData();
        try {
            Game game = gameRepository.findOne(gameId);
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
            gameConverter.copyFromEntityToData(game, gameData);
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("addPlayer throws error.", ex);
            throw ex;
        }
        return gameData;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<UserGameData> getAllGamesForPlayer(UserData userData) {
        Set<UserGameData> userGameDatas = new HashSet<>();
        try {
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
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("getAllGamesForPlayer throws error.", ex);
            throw ex;
        }
        return userGameDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public void createNewUserGame(UserData userData, GameData game) {
        UserGameScores gameScores = new UserGameScores();
        try {
            gameScores.setGameId(game.getId());
            gameScores.setScore(userData.getGameScore());
            gameScores.setUsername(userData.getUsername());
            userGameScoresRepository.save(gameScores);
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("createNewUserGame throws error.", ex);
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserGameData getUserGameData(String username, Long gameId) {
        try {
            UserGameScores gameScores = userGameScoresRepository.getUserGameScore(username, gameId);
            if (gameScores != null) {
                UserGameData userGameData = new UserGameData();
                userGameConverter.copyFromEntityToData(gameScores, userGameData);
                return userGameData;
            }
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("getUserGameData throws error.", ex);
            throw ex;
        }
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void updateUserGame(String username, Long gameId, Long score) {
        try {
        userGameScoresRepository.updateUserGameScore(username, gameId, score);
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("updateUserGame throws error.", ex);
            throw ex;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void updateGameLetter(String letter, Long gameId) {
        try {
        gameRepository.updateGameLetter(letter, gameId);
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("updateGameLetter throws error.", ex);
            throw ex;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteOldGames(Date beforeDate) {
        try {
        gameRepository.removeOldGames(beforeDate.getTime());
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("deleteOldGames throws error.", ex);
            throw ex;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public GameData lockGame(Long gameId) {        
        GameData gameData = null;
        try {
        Game game = gameRepository.lockGame(gameId);
        if (game != null) {
            gameData = new GameData();
            gameConverter.copyFromEntityToData(game, gameData);
        }
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("lockGame throws error.", ex);
            throw ex;
        }
        return gameData;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<GameData> getUnlockedGames() {
        Set<Game> unlockedGames = new HashSet<>();
        Set<GameData> unlockedGameDatas = new HashSet<>();
        try {
            unlockedGames = gameRepository.getUnlockedGames();
            if (!unlockedGames.isEmpty()) {
                gameConverter.copyFromEntitySetToDataSet(unlockedGames, unlockedGameDatas, new GameData());
            }
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("getUnlockedGames throws error.", ex);
            throw ex;
        }
        return unlockedGameDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public void lockStartedGames(Date startDate) {
        try {
            gameRepository.lockUnlockedGames(startDate.getTime());
        } catch (RuntimeException ex) {
            GEOGRAPHY_LOGGER.error("lockStartedGames throws error.", ex);
            throw ex;
        }
    }

}
