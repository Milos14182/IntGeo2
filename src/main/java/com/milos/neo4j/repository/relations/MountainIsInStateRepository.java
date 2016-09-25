package com.milos.neo4j.repository.relations;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.relations.MountainIsInState;

public interface MountainIsInStateRepository extends CrudRepository<MountainIsInState, Long> {
	
	@Query("MATCH (m:Mountain)-[r:MOUNTAIN_IS_IN_STATE]->(s:State) where m.name={0} and s.name={1} RETURN r")
	MountainIsInState getMountainInState(String mountainName, String stateName);
}
