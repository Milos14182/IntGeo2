package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties("meta")
public class StateData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private String synonims;
    private Set<CityData> cities = new HashSet<CityData>();
    private Set<MountainData> mountains = new HashSet<MountainData>();
    private Set<AnimalData> animals = new HashSet<AnimalData>();
    private Set<LakeData> lakes = new HashSet<LakeData>();
    private Set<PlantData> plantes = new HashSet<PlantData>();
    private Set<RiverData> rivers = new HashSet<RiverData>();

    public StateData() {
    }

    public StateData(String name) {
        this.name = name;
        this.active = false;
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

    public Set<MountainData> getMountains() {
        return mountains;
    }

    public void setMountains(Set<MountainData> mountains) {
        this.mountains = mountains;
    }

    public Set<AnimalData> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<AnimalData> animals) {
        this.animals = animals;
    }

    public Set<LakeData> getLakes() {
        return lakes;
    }

    public void setLakes(Set<LakeData> lakes) {
        this.lakes = lakes;
    }

    public Set<PlantData> getPlantes() {
        return plantes;
    }

    public void setPlantes(Set<PlantData> plantes) {
        this.plantes = plantes;
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
