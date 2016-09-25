package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.River;

@RelationshipEntity(type = "RIVER_FLOWS_THROUGH_CITY")
public class RiverFlowsThroughCity {
	@GraphId
	private Long id;
	@StartNode
	private River river;
	@EndNode
	private City city;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
