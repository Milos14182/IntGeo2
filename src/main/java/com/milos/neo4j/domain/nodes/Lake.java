package com.milos.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Lake {
	private @GraphId Long id;

	@Property(name = "name")
	private String name;

	@Property(name = "description")
	private String description;

	@Property(name = "active")
	private boolean active;

	@Relationship(type = "RIVER_GOES_IN", direction = Relationship.INCOMING)
	private Set<River> rivers = new HashSet<River>();

	@Relationship(type = "LAKE_IS_IN_STATE", direction = Relationship.OUTGOING)
	private State state;

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

	public Set<River> getRivers() {
		return rivers;
	}

	public void setRivers(Set<River> rivers) {
		this.rivers = rivers;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
