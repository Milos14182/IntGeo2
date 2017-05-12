package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties("meta")
public class LakeData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private StateData stateData;
    private String synonims;
    private Set<RiverData> rivers = new HashSet<RiverData>();
    private Set<AnimalData> animals = new HashSet<AnimalData>();

    public StateData getStateData() {
        return stateData;
    }

    public void setStateData(StateData stateData) {
        this.stateData = stateData;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Set<AnimalData> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalData> animals) {
        this.animals = animals;
    }

    public Set<RiverData> getRivers() {
        return rivers;
    }

    public void setRivers(Set<RiverData> rivers) {
        this.rivers = rivers;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }
}
