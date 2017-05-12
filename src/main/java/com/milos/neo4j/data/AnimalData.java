package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties("meta")
public class AnimalData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private String synonims;
    private Set<StateData> states = new HashSet<StateData>();
    private Set<MountainData> mountains = new HashSet<MountainData>();
    private Set<CityData> cities = new HashSet<CityData>();
    private Set<RiverData> rivers = new HashSet<RiverData>();
    private Set<LakeData> lakes = new HashSet<LakeData>();

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

    public Set<StateData> getStates() {
        return states;
    }

    public void setStates(Set<StateData> states) {
        this.states = states;
    }

    public Set<MountainData> getMountains() {
        return mountains;
    }

    public void setMountains(Set<MountainData> mountains) {
        this.mountains = mountains;
    }

    public Set<CityData> getCities() {
        return cities;
    }

    public void setCities(Set<CityData> cities) {
        this.cities = cities;
    }

    public Set<RiverData> getRivers() {
        return rivers;
    }

    public void setRivers(Set<RiverData> rivers) {
        this.rivers = rivers;
    }

    public Set<LakeData> getLakes() {
        return lakes;
    }

    public void setLakes(Set<LakeData> lakes) {
        this.lakes = lakes;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }
}
