package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Game;
import java.util.Date;

public interface GameRepository extends GraphRepository<Game> {

    @Query("MATCH (n:Game) WHERE n.activeGame = {0} RETURN n")
    public Set<Game> findAllInactiveGames(Boolean flag);

    @Query("MATCH (n:Game) WHERE n.id = {1} SET n.firstLetter = {0} RETURN n")
    public Game updateGameLetter(String letter, Long id);
    
    @Query("MATCH (n:Game)-[rel]->() where n.cretionDate < {0} DELETE s,rel")
    public void removeOldGames(Date beforeDate);
    
    @Query("MATCH (n:Game) where n.id = {0} SET n.locked = true RETURN s")
    public Game lockGame(Long id);
}
