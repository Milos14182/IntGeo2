package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.Animal;
import com.milos.neo4j.domain.nodes.Lake;

@RelationshipEntity(type = "ANIMAL_IS_IN_LAKE")
public class AnimalIsInLake {
	@GraphId
	private Long id;
	@StartNode
	private Animal animal;
	@EndNode
	private Lake lake;

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

	public Lake getLake() {
		return lake;
	}

	public void setLake(Lake lake) {
		this.lake = lake;
	}

}
