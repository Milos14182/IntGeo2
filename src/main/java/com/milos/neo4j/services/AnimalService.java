package com.milos.neo4j.services;

import com.milos.neo4j.data.AnimalData;

public interface AnimalService {
	public AnimalData getAnimalByName(String name);
	public void saveAnimal(AnimalData animalData);
}
