package com.milos.neo4j.services;

import java.util.Set;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.data.UserGameData;

public interface GameService {
	public GameData getGameById(Long id);
	public GameData createNewGame(UserData userData);
	public Set<GameData> getAllInactiveGames();
	public GameData addPlayer(UserData userData, Long gameId);
	public Set<UserGameData> getAllGamesForPlayer(UserData userData);
	public UserGameData getUserGameData(String username, Long gameId);
	public void createNewUserGame(UserData userData, GameData game);
	public void updateUserGame(String username, Long gameId, Long score);
}
