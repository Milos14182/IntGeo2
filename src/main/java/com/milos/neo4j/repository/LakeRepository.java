package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Lake;

public interface LakeRepository extends GraphRepository<Lake> {
	@Query("MATCH (s:Lake) WHERE s.name = {0} RETURN s")
	public Lake getLakeByName(String name);
                
        @Query("MATCH (s:Lake) WHERE s.name = {0} or s.synonims CONTAINS {1} RETURN s")
	public Lake getLakeByNameOrSynonim(String name, String synonim);
	
	@Query("MATCH (s:Lake) where s.active = {0} RETURN s")
	public Set<Lake> getInactiveLakes(boolean active);
	
	@Query("MATCH (s:Lake) where id(s) = {0} SET s.active = {1} RETURN s")
	public Lake setLakeToActive(Long id, boolean active);
}
