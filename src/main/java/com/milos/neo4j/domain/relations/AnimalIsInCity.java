package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.*;

@RelationshipEntity(type = "ANIMAL_IS_IN_CITY")
public class AnimalIsInCity {
	@GraphId
	private Long id;
	@StartNode
	private Animal animal;
	@EndNode
	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
