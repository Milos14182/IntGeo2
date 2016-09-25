package com.milos.neo4j.repository.relations;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.relations.RiverFlowsThroughState;

public interface RiverFlowsThroughStateRepository extends CrudRepository<RiverFlowsThroughState, Long> {
	@Query("MATCH (n:River)-[r:RIVER_FLOWS_THROUGH_STATE]->(s:State) where n.name={0} and s.name={1} RETURN r")
	RiverFlowsThroughState getRiverStateRelation(String riverName, String stateName);
}
