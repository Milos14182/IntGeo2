package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.River;

public interface RiverRepository extends GraphRepository<River> {
	@Query("MATCH (s:River) WHERE s.name = {0} RETURN s")
	public River getRiverByName(String name);
	
	@Query("MATCH (s:River) where s.active = {0} RETURN s")
	public Set<River> getInactiveRiver(boolean active);
	
	@Query("MATCH (s:River) where id(s) = {0} SET s.active = {1} RETURN s")
	public River setRiverToActive(Long id, boolean active);
}
