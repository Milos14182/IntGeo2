package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Mountain;

public interface MountainRepository extends GraphRepository<Mountain> {

    @Query("MATCH (s:Mountain) WHERE s.name = {0} RETURN s")
    public Mountain getMountainByName(String name);

    @Query("MATCH (s:Mountain) WHERE s.name = {0} or (s.synonims is not null and s.synonims={1}) RETURN s")
    public Mountain getMountainByNameOrSynonim(String name, String synonim);

    @Query("MATCH (s:Mountain) where s.active = {0} RETURN s")
    public Set<Mountain> getInactiveMountains(boolean active);

    @Query("MATCH (s:Mountain) where id(s) = {0} SET s.active = {1} RETURN s")
    public Mountain setMountainToActive(Long id, boolean active);
}
