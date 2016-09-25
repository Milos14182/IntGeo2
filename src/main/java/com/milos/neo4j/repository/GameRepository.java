package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Game;

public interface GameRepository extends GraphRepository<Game> {
	@Query("MATCH (n:Game) WHERE n.activeGame = {0} RETURN n")
	public Set<Game> findAllInactiveGames(Boolean flag);
}
