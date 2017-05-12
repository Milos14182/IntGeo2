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
public class Animal {

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

    @Relationship(type = "ANIMAL_IS_IN_STATE", direction = Relationship.OUTGOING)
    private Set<State> states = new HashSet<State>();

    @Relationship(type = "ANIMAL_IS_ON_MOUNTAIN", direction = Relationship.OUTGOING)
    private Set<Mountain> mountains = new HashSet<Mountain>();

    @Relationship(type = "ANIMAL_IS_IN_CITY", direction = Relationship.OUTGOING)
    private Set<City> cities;

    @Relationship(type = "ANIMAL_IS_IN_RIVER", direction = Relationship.OUTGOING)
    private Set<River> rivers;

    @Relationship(type = "ANIMAL_IS_IN_LAKE", direction = Relationship.OUTGOING)
    private Set<Lake> lakes;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Set<Mountain> getMountains() {
        return mountains;
    }

    public void setMountains(Set<Mountain> mountains) {
        this.mountains = mountains;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<River> getRivers() {
        return rivers;
    }

    public void setRivers(Set<River> rivers) {
        this.rivers = rivers;
    }

    public Set<Lake> getLakes() {
        return lakes;
    }

    public void setLakes(Set<Lake> lakes) {
        this.lakes = lakes;
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
