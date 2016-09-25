package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.Lake;

@RelationshipEntity(type="LAKE_NEAR_BY_CITY")
public class LakeNearByCity {
	@GraphId
	private Long id;
	@StartNode private Lake lake;
	@EndNode
	private City city;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
