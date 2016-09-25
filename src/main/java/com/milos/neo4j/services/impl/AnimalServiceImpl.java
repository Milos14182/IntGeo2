package com.milos.neo4j.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.AnimalConverter;
import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.domain.nodes.Animal;
import com.milos.neo4j.repository.AnimalRepository;
import com.milos.neo4j.services.AnimalService;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService {
	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	AnimalConverter animalConverter;

	public AnimalData getAnimalByName(String name) {
		AnimalData animalData = null;
		Animal animal = animalRepository.getAnimalByName(name);
		if (animal != null) {
			animalData = new AnimalData();
			animalConverter.copyFromEntityToData(animal, animalData);
		}
		return animalData;
	}

	@Override
	public void saveAnimal(AnimalData animalData) {
		Animal animal = new Animal();
		animalConverter.copyFromDataToEntity(animalData, animal);
		animalRepository.save(animal);		
	}

}
