package com.milos.neo4j.services;

import java.util.Set;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.data.UserGameData;
import java.util.Date;

public interface GameService {

    public GameData getGameById(Long id);

    public GameData createNewGame(UserData userData, Integer gamePoints);

    public Set<GameData> getAllInactiveGames();

    public GameData addPlayer(UserData userData, Long gameId);

    public Set<UserGameData> getAllGamesForPlayer(UserData userData);

    public UserGameData getUserGameData(String username, Long gameId);

    public void createNewUserGame(Long gameId, String username, Long score);

    public void updateUserGame(String username, Long gameId, Long score);

    public void updateGameLetter(String letter, Long gameId, Integer round, Long date);
    
    public void deleteOldGames(Date beforeDate);
    
    public GameData lockGame(Long gameId);
    
    public Set<GameData> getUnlockedGames();
    
    public void lockStartedGames(Date startDate);

    public Boolean checkIsLocked(Long gameId);
    
    public Set<GameData> getUserGames(UserData userData);
    
    public String getPreviousLetters(Long gameId);
    
    public void updatePreviousLetters(Long gameId, String previouslySelectedLetters);
    
    void deleteUserGameScoresBeforeDate(Date beforeDate);
    
    GameData endGame(Long gameId);
}
