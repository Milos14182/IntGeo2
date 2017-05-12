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
public class City {

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

    @Relationship(type = "CITY_IS_IN_STATE", direction = Relationship.OUTGOING)
    private State state;

    @Relationship(type = "LIVES_IN_CITY", direction = Relationship.INCOMING)
    private Set<User> users = new HashSet<User>();

    @Relationship(type = "PLANT_HABITATION", direction = Relationship.INCOMING)
    private Set<Plant> plants = new HashSet<Plant>();

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
