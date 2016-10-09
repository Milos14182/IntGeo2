package com.milos.neo4j.repository.relations;


import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.nodes.Game;
import com.milos.neo4j.domain.relations.GameRelation;

public interface GameRelationRepository extends CrudRepository<GameRelation, Long> {

	@Query("MATCH (u:User)-[r:GAME_RELATION]->(c:Game) WHERE u.username = {0} RETURN r")
	public GameRelation findGameRelationForUser (String username);
	
	@Query("MATCH (u:User)-[r:GAME_RELATION]-(g:Game) where u.username={0} RETURN g")
	public Set<Game> findGamesForUser (String username);
}
