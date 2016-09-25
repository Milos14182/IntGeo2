package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.Plant;

@RelationshipEntity(type = "PLANT_HABITATION")
public class PlantHabitation {
	@GraphId
	private Long id;
	@StartNode
	private Plant plant;
	@EndNode
	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
