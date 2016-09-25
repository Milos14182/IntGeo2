package com.milos.neo4j.repository.relations;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.relations.CityIsInState;

public interface CityIsInStateRepository extends CrudRepository<CityIsInState, Long> {
	
	@Query("MATCH (n:City)-[r:CITY_IS_IN_STATE]->(s:State) where n.name={0} and s.name={1} RETURN r")
	CityIsInState getStateOfCity(String cityName, String stateName);
}
