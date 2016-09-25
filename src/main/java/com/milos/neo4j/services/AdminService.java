package com.milos.neo4j.services;

import java.util.Set;

import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;

public interface AdminService {
	public Set<AnimalData> getInactiveAnimalDatas();
	public Set<CityData> getInactiveCityDatas();
	public Set<MountainData> getInactiveMountainDatas();
	public Set<LakeData> getInactiveLakeDatas();
	public Set<StateData> getInactiveStateDatas();
	public Set<RiverData> getInactiveRiverDatas();
	public Set<PlantData> getInactivePlantDatas();
	
	public AnimalData setAnimalDataActive(Long id, boolean active);
	public CityData setCityDataActive(Long id, boolean active);
	public MountainData setMountainDataActive(Long id, boolean active);
	public LakeData setLakeDataActive(Long id, boolean active);
	public StateData setStateDataActive(Long id, boolean active);
	public RiverData setRiverDataActive(Long id, boolean active);
	public PlantData setPlantDataActive(Long id, boolean active);
	
	public void removeAnimal(Long id);
	public void removeCity(Long id);
	public void removeState(Long id);
	public void removeMountain(Long id);
	public void removelake(Long id);
	public void removeRiver(Long id);
	public void removePlant(Long id);
	
	public AnimalData getAnimalById (Long id);
	public CityData getCityById (Long id);
	public MountainData getMountainById (Long id);
	public StateData getStateById (Long id);
	public RiverData getRiverById (Long id);
	public LakeData getLakeById (Long id);
	public PlantData getPlantById (Long id);
}
