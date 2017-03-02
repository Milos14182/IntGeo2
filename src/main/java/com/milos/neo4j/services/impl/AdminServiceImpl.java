package com.milos.neo4j.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.AnimalConverter;
import com.milos.neo4j.converter.CityConverter;
import com.milos.neo4j.converter.LakeConverter;
import com.milos.neo4j.converter.MountainConverter;
import com.milos.neo4j.converter.PlantConverter;
import com.milos.neo4j.converter.RiverConverter;
import com.milos.neo4j.converter.StateConverter;
import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;
import com.milos.neo4j.domain.nodes.Animal;
import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.Lake;
import com.milos.neo4j.domain.nodes.Mountain;
import com.milos.neo4j.domain.nodes.Plant;
import com.milos.neo4j.domain.nodes.River;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.AnimalRepository;
import com.milos.neo4j.repository.CityRepository;
import com.milos.neo4j.repository.LakeRepository;
import com.milos.neo4j.repository.MountainRepository;
import com.milos.neo4j.repository.PlantRepository;
import com.milos.neo4j.repository.RiverRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.services.AdminService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    RiverRepository riverRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    MountainRepository mountainRepository;

    @Autowired
    LakeRepository lakeRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalConverter animalConverter;

    @Autowired
    CityConverter cityConverter;

    @Autowired
    LakeConverter lakeConverter;

    @Autowired
    StateConverter stateConverter;

    @Autowired
    MountainConverter mountainConverter;

    @Autowired
    PlantConverter plantConverter;

    @Autowired
    RiverConverter riverConverter;

    @Transactional(readOnly = true)
    @Override
    public Set<AnimalData> getInactiveAnimalDatas() {
        Set<Animal> inactiveAnimalNodes = animalRepository.getInactiveAnimals(false);
        Set<AnimalData> inactiveAnimalDatas = new HashSet<>();
        animalConverter.copyFromEntitySetToDataSet(inactiveAnimalNodes, inactiveAnimalDatas, new AnimalData());
        return inactiveAnimalDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<CityData> getInactiveCityDatas() {
        Set<City> inactiveCityNodes = cityRepository.getInactiveCitys(false);
        Set<CityData> inactiveCityDatas = new HashSet<>();
        cityConverter.copyFromEntitySetToDataSet(inactiveCityNodes, inactiveCityDatas, new CityData());
        return inactiveCityDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<MountainData> getInactiveMountainDatas() {
        Set<Mountain> inactiveMountainNodes = mountainRepository.getInactiveMountains(false);
        Set<MountainData> inactiveMountainDatas = new HashSet<>();
        mountainConverter.copyFromEntitySetToDataSet(inactiveMountainNodes, inactiveMountainDatas, new MountainData());
        return inactiveMountainDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<LakeData> getInactiveLakeDatas() {
        Set<Lake> inactiveLakeNodes = lakeRepository.getInactiveLakes(false);
        Set<LakeData> inactiveLakeDatas = new HashSet<>();
        lakeConverter.copyFromEntitySetToDataSet(inactiveLakeNodes, inactiveLakeDatas, new LakeData());
        return inactiveLakeDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<StateData> getInactiveStateDatas() {
        Set<State> inactiveStateNodes = stateRepository.getInactiveState(false);
        Set<StateData> inactiveStateDatas = new HashSet<>();
        stateConverter.copyFromEntitySetToDataSet(inactiveStateNodes, inactiveStateDatas, new StateData());
        return inactiveStateDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<RiverData> getInactiveRiverDatas() {
        Set<River> inactiveRiverNodes = riverRepository.getInactiveRiver(false);
        Set<RiverData> inactiveRiverDatas = new HashSet<>();
        riverConverter.copyFromEntitySetToDataSet(inactiveRiverNodes, inactiveRiverDatas, new RiverData());
        return inactiveRiverDatas;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<PlantData> getInactivePlantDatas() {
        Set<Plant> inactivePlantNodes = plantRepository.getInactivePlants(false);
        Set<PlantData> inactivePlantDatas = new HashSet<>();
        plantConverter.copyFromEntitySetToDataSet(inactivePlantNodes, inactivePlantDatas, new PlantData());
        return inactivePlantDatas;
    }

    @Transactional(readOnly = false)
    @Override
    public AnimalData setAnimalDataActive(Long id, boolean active) {
        AnimalData animalData = new AnimalData();
        Animal animal = animalRepository.setAnimalToActive(id, active);
        animalConverter.copyFromEntityToData(animal, animalData);
        return animalData;
    }

    @Transactional(readOnly = false)
    @Override
    public CityData setCityDataActive(Long id, boolean active) {
        CityData cityData = new CityData();
        City city = cityRepository.setCityToActive(id, active);
        cityConverter.copyFromEntityToData(city, cityData);
        return cityData;
    }

    @Transactional(readOnly = false)
    @Override
    public MountainData setMountainDataActive(Long id, boolean active) {
        MountainData mountainData = new MountainData();
        Mountain mountain = mountainRepository.setMountainToActive(id, active);
        mountainConverter.copyFromEntityToData(mountain, mountainData);
        return mountainData;
    }

    @Transactional(readOnly = false)
    @Override
    public LakeData setLakeDataActive(Long id, boolean active) {
        LakeData lakeData = new LakeData();
        Lake lake = lakeRepository.setLakeToActive(id, active);
        lakeConverter.copyFromEntityToData(lake, lakeData);
        return lakeData;
    }

    @Transactional(readOnly = false)
    @Override
    public StateData setStateDataActive(Long id, boolean active) {
        StateData stateData = new StateData();
        State state = stateRepository.setStateToActive(id, active);
        stateConverter.copyFromEntityToData(state, stateData);
        return stateData;
    }

    @Transactional(readOnly = false)
    @Override
    public RiverData setRiverDataActive(Long id, boolean active) {
        RiverData riverData = new RiverData();
        River river = riverRepository.setRiverToActive(id, active);
        riverConverter.copyFromEntityToData(river, riverData);
        return riverData;
    }

    @Transactional(readOnly = false)
    @Override
    public PlantData setPlantDataActive(Long id, boolean active) {
        PlantData plantData = new PlantData();
        Plant plant = plantRepository.setPlantToActive(id, active);
        plantConverter.copyFromEntityToData(plant, plantData);
        return plantData;
    }

    @Transactional(readOnly = false)
    @Override
    public void removeAnimal(Long id) {
        animalRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeCity(Long id) {
        cityRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeState(Long id) {
        stateRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeMountain(Long id) {
        mountainRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removelake(Long id) {
        lakeRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeRiver(Long id) {
        riverRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removePlant(Long id) {
        plantRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AnimalData getAnimalById(Long id) {
        Animal animal = animalRepository.findOne(id);
        AnimalData animalData = new AnimalData();
        animalConverter.copyFromEntityToData(animal, animalData);
        return animalData;
    }

    @Transactional(readOnly = true)
    @Override
    public CityData getCityById(Long id) {
        City city = cityRepository.findOne(id);
        CityData cityData = new CityData();
        cityConverter.copyFromEntityToData(city, cityData);
        return cityData;
    }

    @Transactional(readOnly = true)
    @Override
    public MountainData getMountainById(Long id) {
        Mountain mountain = mountainRepository.findOne(id);
        MountainData mountainData = new MountainData();
        mountainConverter.copyFromEntityToData(mountain, mountainData);
        return mountainData;
    }

    @Transactional(readOnly = true)
    @Override
    public StateData getStateById(Long id) {
        State state = stateRepository.findOne(id);
        StateData stateData = new StateData();
        stateConverter.copyFromEntityToData(state, stateData);
        return stateData;
    }

    @Transactional(readOnly = true)
    @Override
    public RiverData getRiverById(Long id) {
        River river = riverRepository.findOne(id);
        RiverData riverData = new RiverData();
        riverConverter.copyFromEntityToData(river, riverData);
        return riverData;
    }

    @Transactional(readOnly = true)
    @Override
    public LakeData getLakeById(Long id) {
        Lake lake = lakeRepository.findOne(id);
        LakeData lakeData = new LakeData();
        lakeConverter.copyFromEntityToData(lake, lakeData);
        return lakeData;
    }

    @Transactional(readOnly = true)
    @Override
    public PlantData getPlantById(Long id) {
        Plant plant = plantRepository.findOne(id);
        PlantData plantData = new PlantData();
        plantConverter.copyFromEntityToData(plant, plantData);
        return plantData;
    }

}
