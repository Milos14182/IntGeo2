package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.Lake;
import com.milos.neo4j.domain.nodes.River;

@RelationshipEntity(type = "RIVER_GOES_IN")
public class RiverGoesIn {
	@GraphId
	private Long id;
	@StartNode
	private River river;
	@EndNode
	private Lake lake;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public Lake getLake() {
		return lake;
	}

	public void setLake(Lake lake) {
		this.lake = lake;
	}

}
