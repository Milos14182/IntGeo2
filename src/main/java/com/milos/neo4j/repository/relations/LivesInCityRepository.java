package com.milos.neo4j.repository.relations;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.milos.neo4j.domain.relations.LivesInCity;

public interface LivesInCityRepository extends CrudRepository<LivesInCity, Long> {

    @Query("MATCH (u:User)-[r:LIVES_IN_CITY]->(c:City) WHERE u.username = {0} RETURN r")
    LivesInCity getUserByUsername(String username);

    @Query("MATCH (u:User), (c:City) WHERE u.username = {0} AND c.name = {1} CREATE (u)-[r:LIVES_IN_CITY]->(c)RETURN r")
    LivesInCity saveUserInCity(String username, String cityName);
    
    @Query("MATCH (u:User)-[r:LIVES_IN_CITY]->(c:City) WHERE u.username = {0} DELETE r")
    void deleteUserCity(String username);
}
