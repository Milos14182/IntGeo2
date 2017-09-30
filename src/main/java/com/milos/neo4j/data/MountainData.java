package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("meta")
public class MountainData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private StateData stateData;
    private String synonims;
    private Set<CityData> cities = new HashSet<CityData>();
    private Set<AnimalData> animals = new HashSet<AnimalData>();

    public MountainData(String name) {
        this.name = name;
        this.active = false;
    }

    public MountainData() {
    }

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

    public Set<CityData> getCities() {
        return cities;
    }

    public void setCities(Set<CityData> cities) {
        this.cities = cities;
    }

    public Set<AnimalData> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalData> animals) {
        this.animals = animals;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }
}
