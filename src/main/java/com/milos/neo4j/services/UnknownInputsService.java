/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services;

import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;
import java.util.Map;

/**
 *
 * @author milos
 */
public interface UnknownInputsService {
    
    public void saveUnknownIntoDatabase();
    
    Map<String, StateData> getStates();
    
    Map<String, MountainData> getMountains();
    
    Map<String, RiverData> getRivers();
    
    Map<String, CityData> getCitys();
    
    Map<String, LakeData> getLakes();
    
    Map<String, AnimalData> getAnimals();
    
    Map<String, PlantData> getPlants();
}
