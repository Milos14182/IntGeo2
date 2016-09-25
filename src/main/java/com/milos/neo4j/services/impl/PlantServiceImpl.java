package com.milos.neo4j.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.PlantConverter;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.domain.nodes.Plant;
import com.milos.neo4j.repository.PlantRepository;
import com.milos.neo4j.services.PlantService;

@Service ("plantService")
public class PlantServiceImpl implements PlantService {
	@Autowired
	PlantRepository plantRepository;
	
	@Autowired
	PlantConverter plantConverter;
	
	public PlantData getPlantByName(String name) {
		PlantData plantData = null;
		Plant plant = plantRepository.getPlantByName(name);
		if (plant != null) {
			plantData = new PlantData();
			plantConverter.copyFromEntityToData(plant, plantData);
		}
		return plantData;
	}

	@Override
	public void savePlant(PlantData plantData) {
		Plant plant = new Plant();
		plantConverter.copyFromDataToEntity(plantData, plant);
		plantRepository.save(plant);
	}

}
