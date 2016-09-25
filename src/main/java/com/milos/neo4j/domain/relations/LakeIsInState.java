package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.Lake;
import com.milos.neo4j.domain.nodes.State;

@RelationshipEntity(type="LAKE_IS_IN_STATE")
public class LakeIsInState {
	@GraphId
	private Long id;
	@StartNode private Lake lake;
	@EndNode
	private State state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lake getLake() {
		return lake;
	}

	public void setLake(Lake lake) {
		this.lake = lake;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
