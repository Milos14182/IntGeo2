package com.milos.neo4j.services;

import java.util.Set;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;

public interface GameService {
	public GameData getGameById(Long id);
	public GameData createNewGame(UserData userData);
	public Set<GameData> getAllInactiveGames();
	public GameData addPlayer(UserData userData, Long gameId);
}
