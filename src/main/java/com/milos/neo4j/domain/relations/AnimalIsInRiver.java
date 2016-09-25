package com.milos.neo4j.domain.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.milos.neo4j.domain.nodes.Animal;
import com.milos.neo4j.domain.nodes.River;

@RelationshipEntity(type = "ANIMAL_IS_IN_RIVER")
public class AnimalIsInRiver {
	@GraphId
	private Long id;
	@StartNode
	private Animal animal;
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
	public River getRiver() {
		return river;
	}
	public void setRiver(River river) {
		this.river = river;
	}
	@EndNode
	River river;
}
