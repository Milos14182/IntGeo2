package com.milos.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private @GraphId
    Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "description")
    private String description;

    @Property(name = "active")
    private boolean active;

    @Property(name = "synonims")
    private String synonims;

    @Relationship(type = "CITY_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<City> cities = new HashSet<City>();

    @Relationship(type = "MOUNTAIN_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<Mountain> mountains = new HashSet<Mountain>();

    @Relationship(type = "ANIMAL_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<Animal> animals = new HashSet<Animal>();

    @Relationship(type = "LAKE_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<Lake> lakes = new HashSet<Lake>();

    @Relationship(type = "PLANT_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<Plant> plantes = new HashSet<Plant>();

    @Relationship(type = "RIVER_IS_IN_STATE", direction = Relationship.INCOMING)
    private Set<River> rivers = new HashSet<River>();

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<Mountain> getMountains() {
        return mountains;
    }

    public void setMountains(Set<Mountain> mountains) {
        this.mountains = mountains;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Set<Lake> getLakes() {
        return lakes;
    }

    public void setLakes(Set<Lake> lakes) {
        this.lakes = lakes;
    }

    public Set<Plant> getPlantes() {
        return plantes;
    }

    public void setPlantes(Set<Plant> plantes) {
        this.plantes = plantes;
    }

    public Set<River> getRivers() {
        return rivers;
    }

    public void setRivers(Set<River> rivers) {
        this.rivers = rivers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }

}
