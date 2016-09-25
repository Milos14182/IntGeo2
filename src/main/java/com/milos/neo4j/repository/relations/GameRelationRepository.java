package com.milos.neo4j.repository.relations;


import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.relations.GameRelation;

public interface GameRelationRepository extends CrudRepository<GameRelation, Long> {

	@Query("MATCH (u:User)-[r:GAME_RELATION]->(c:Game) WHERE u.username = {0} RETURN r")
	public GameRelation findGameRelationForUser (String username);
}
