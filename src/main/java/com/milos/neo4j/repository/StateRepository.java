package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.State;

public interface StateRepository extends GraphRepository<State> {
	@Query("MATCH (s:State) WHERE s.name = {0} RETURN s")
	public State getStateByName(String name);
        
        @Query("MATCH (s:State) WHERE s.name = {0} or s.synonims CONTAINS {1} RETURN s")
	public State getStateByNameOrSynonim(String name, String synonim);
	
	@Query("MATCH (s:State) where s.active = {0} RETURN s")
	public Set<State> getInactiveState(boolean active);
	
	@Query("MATCH (s:State) where id(s) = {0} SET s.active = {1} RETURN s")
	public State setStateToActive(Long id, boolean active);
}
