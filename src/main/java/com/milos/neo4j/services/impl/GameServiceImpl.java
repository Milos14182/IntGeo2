package com.milos.neo4j.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.GameConverter;
import com.milos.neo4j.converter.UserConverter;
import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.Game;
import com.milos.neo4j.domain.nodes.User;
import com.milos.neo4j.domain.relations.GameRelation;
import com.milos.neo4j.repository.GameRepository;
import com.milos.neo4j.repository.UserRepository;
import com.milos.neo4j.repository.relations.GameRelationRepository;
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.PlayService;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepository gameRepository;

	@Autowired
	GameRelationRepository gameRelationRepository;

	@Autowired
	GameConverter gameConverter;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	PlayService playService;

	@Override
	public GameData getGameById(Long id) {
		GameData gameData = new GameData();
		gameConverter.copyFromEntityToData(gameRepository.findOne(id), gameData);
		return gameData;
	}

	@Override
	public GameData createNewGame(UserData userData) {
		User user = userRepository.findOne(userData.getId());
		GameRelation gameRelation = gameRelationRepository.findGameRelationForUser(userData.getUsername());
		GameData gameData = null;
		Game game = null;
		if (user != null && gameRelation == null) {
			game = new Game();
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

	@Override
	public Set<GameData> getAllInactiveGames() {
		Set<Game> inactiveGames = gameRepository.findAllInactiveGames(Boolean.valueOf(false));
		Set<GameData> inactiveGameDatas = new HashSet<>();
		gameConverter.copyFromEntitySetToDataSet(inactiveGames, inactiveGameDatas, new GameData());
		return inactiveGameDatas;
	}

	@Override
	public GameData addPlayer(UserData userData, Long gameId) {
		Game game = gameRepository.findOne(gameId);
		User user = new User();
		userConverter.copyFromDataToEntity(userData, user);
		Set<User> players = game.getPlayers();
		boolean isInSet = false;
		for (User player : players) {
			if(player.getUsername().equals(userData.getUsername())) {
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

	

}
