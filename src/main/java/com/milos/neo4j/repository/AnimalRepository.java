package com.milos.neo4j.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.Animal;

public interface AnimalRepository extends GraphRepository<Animal> {

    @Query("MATCH (s:Animal) WHERE s.name = {0} or s.synonims CONTAINS {1} RETURN s")
    public Animal getAnimalByNameOrSynonim(String name, String synonim);

    @Query("MATCH (s:Animal) where s.active = {0} RETURN s")
    public Set<Animal> getInactiveAnimals(boolean active);

    @Query("MATCH (s:Animal) where id(s) = {0} SET s.active = {1} RETURN s")
    public Animal setAnimalToActive(Long id, boolean active);
}
