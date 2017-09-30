package com.milos.neo4j.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("meta")
public class RiverData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private String synonims;
    private Set<StateData> stateDatas = new HashSet<StateData>();
    private Set<CityData> cityDatas = new HashSet<CityData>();
    private Set<AnimalData> animalDatas = new HashSet<AnimalData>();
    private List<Long> stateIds = new ArrayList<>();
    private LakeData lake;

    public RiverData(String name) {
        this.name = name;
        this.active = false;
    }

    public RiverData() {
    }

    public List<Long> getStateIds() {
        return stateIds;
    }

    public void setStateIds(List<Long> stateIds) {
        this.stateIds = stateIds;
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

    public Set<StateData> getStateDatas() {
        return stateDatas;
    }

    public void setStateDatas(Set<StateData> stateDatas) {
        this.stateDatas = stateDatas;
    }

    public Set<CityData> getCityDatas() {
        return cityDatas;
    }

    public void setCityDatas(Set<CityData> cityDatas) {
        this.cityDatas = cityDatas;
    }

    public Set<AnimalData> getAnimalDatas() {
        return animalDatas;
    }

    public void setAnimalDatas(Set<AnimalData> animalDatas) {
        this.animalDatas = animalDatas;
    }

    public LakeData getLake() {
        return lake;
    }

    public void setLake(LakeData lake) {
        this.lake = lake;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }

}
