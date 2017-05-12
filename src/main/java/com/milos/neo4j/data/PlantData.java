package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.milos.neo4j.domain.nodes.State;
import java.util.List;

@JsonIgnoreProperties("meta")
public class PlantData {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private State state;
    private String synonims;
    private Set<CityData> cities = new HashSet<CityData>();

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<CityData> getCities() {
        return cities;
    }

    public void setCities(Set<CityData> cities) {
        this.cities = cities;
    }

    public String getSynonims() {
        return synonims;
    }

    public void setSynonims(String synonims) {
        this.synonims = synonims;
    }

}
