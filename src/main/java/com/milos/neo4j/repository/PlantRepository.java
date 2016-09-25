package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Plant;

public interface PlantRepository extends GraphRepository<Plant> {
	@Query("MATCH (s:Plant) WHERE s.name = {0} RETURN s")
	public Plant getPlantByName(String name);
	
	@Query("MATCH (s:Plant) where s.active = {0} RETURN s")
	public Set<Plant> getInactivePlants(boolean active);
	
	@Query("MATCH (s:Plant) where id(s) = {0} SET s.active = {1} RETURN s")
	public Plant setPlantToActive(Long id, boolean active);
}
