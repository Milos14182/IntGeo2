package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Game;
import java.util.Date;
import java.util.List;

public interface GameRepository extends GraphRepository<Game> {

    @Query("MATCH (n:Game) WHERE n.locked = false RETURN n")
    public Set<Game> findAllInactiveGames(Boolean flag);

    @Query("MATCH (n:Game) WHERE ID(n) = {1} SET n.firstLetter = {0}, n.currentRound = {2}, n.roundStartDate = {3} RETURN n")
    public Game updateGameLetter(String letter, Long id, Integer round, Long date);
    
    @Query("MATCH (n:Game)-[rel]->() WHERE n.creationDate < {0} DELETE n,rel")
    public void removeOldGames(Long beforeDate);
    
    @Query("MATCH (n:Game) WHERE ID(n) = {0} SET n.locked = true RETURN n")
    public Game lockGame(Long id);
    
    @Query("MATCH (n:Game) WHERE n.locked = false RETURN n")
    public Set<Game> getUnlockedGames();
    
    @Query("MATCH (n:Game) WHERE n.locked = false AND n.creationDate < {0} SET n.locked = true RETURN n")
    public void lockUnlockedGames(Long startDate);

    @Query("MATCH (n:Game) WHERE ID(n) = {0} return n.locked")
    public Boolean checkIsLocked(Long gameId);
}
