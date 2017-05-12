package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties("meta")
public class CityData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private StateData stateData;
    private String synonims;
    private Set<UserData> users = new HashSet<UserData>();
    private Set<PlantData> plants = new HashSet<PlantData>();
    private Set<RiverData> rivers = new HashSet<RiverData>();
    private Set<LakeData> lakes = new HashSet<LakeData>();
    private Set<AnimalData> animals = new HashSet<AnimalData>();
    private Set<MountainData> mountains = new HashSet<MountainData>();

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

    public Set<PlantData> getPlants() {
        return plants;
    }

    public void setPlants(Set<PlantData> plants) {
        this.plants = plants;
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

    public Set<AnimalData> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalData> animals) {
        this.animals = animals;
    }

    public Set<MountainData> getMountains() {
        return mountains;
    }

    public void setMountains(Set<MountainData> mountains) {
        this.mountains = mountains;
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

    public Set<UserData> getUsers() {
        return users;
    }

    public void setUsers(Set<UserData> users) {
        this.users = users;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }

}
