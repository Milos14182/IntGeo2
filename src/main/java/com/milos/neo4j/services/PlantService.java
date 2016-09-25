package com.milos.neo4j.services;

import com.milos.neo4j.data.PlantData;

public interface PlantService {
	public PlantData getPlantByName(String name);
	public void savePlant(PlantData plantData);
}
