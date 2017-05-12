package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.City;

public interface CityRepository extends GraphRepository<City> {

    @Query("MATCH (s:City) WHERE s.name = {0} RETURN s")
    public City getCityByName(String name);

    @Query("MATCH (s:City) WHERE s.name = {0} or (s.synonims is not null and s.synonims={1}) RETURN s")
    public City getCityByNameOrSynonim(String name, String synonim);

    @Query("MATCH (s:City) RETURN s ORDER BY s.name ASC")
    public Set<City> getAllCities();

    @Query("MATCH (s:City) where s.active = {0} RETURN s")
    public Set<City> getInactiveCitys(boolean active);

    @Query("MATCH (s:City) where id(s) = {0} SET s.active = {1} RETURN s")
    public City setCityToActive(Long id, boolean active);
}
