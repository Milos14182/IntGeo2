package com.milos.neo4j.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.Animal;
import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.Lake;
import com.milos.neo4j.domain.nodes.Mountain;
import com.milos.neo4j.domain.nodes.Plant;
import com.milos.neo4j.domain.nodes.River;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.domain.relations.CityIsInState;
import com.milos.neo4j.domain.relations.MountainIsInState;
import com.milos.neo4j.domain.relations.RiverFlowsThroughState;
import com.milos.neo4j.enums.LatinAlfabet;
import com.milos.neo4j.repository.AnimalRepository;
import com.milos.neo4j.repository.CityRepository;
import com.milos.neo4j.repository.LakeRepository;
import com.milos.neo4j.repository.MountainRepository;
import com.milos.neo4j.repository.PlantRepository;
import com.milos.neo4j.repository.RiverRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.repository.relations.CityIsInStateRepository;
import com.milos.neo4j.repository.relations.MountainIsInStateRepository;
import com.milos.neo4j.repository.relations.RiverFlowsThroughStateRepository;
import com.milos.neo4j.services.PlayService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Service("playService")
public class PlayServiceImpl implements PlayService {

    private static final long uniqueRightResult = 10;
    private static final long extraPoints = 3;
    @Autowired
    CityRepository cityRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    RiverRepository riverRepository;

    @Autowired
    MountainRepository mountainRepository;

    @Autowired
    LakeRepository lakeRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    MountainIsInStateRepository mountainIsInStateRepository;

    @Autowired
    CityIsInStateRepository cityIsInStateRepository;

    @Autowired
    RiverFlowsThroughStateRepository riverFlowsThroughStateRepository;

    @Transactional(readOnly = true)
    @Override
    public Long countScore(SubmitAnswersTmp answers, UserData userData) {
        MountainIsInState mountainIsInState = null;
        CityIsInState cityIsInState = null;
        RiverFlowsThroughState riverFlowsThroughState = null;
        if (answers.getScore() == null) {
            answers.setScore(Long.valueOf(0));
        }
        if (!answers.getState().equals("") && answers.getState().startsWith(answers.getCharacter())) {
            State state = stateRepository.getStateByName(answers.getState());
            if (state != null && state.isActive()) {
                if (userData.getCity() != null) {
                    cityIsInState = cityIsInStateRepository.getStateOfCity(userData.getCity().getName(),
                            state.getName());
                }
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getAnimal().equals("") && answers.getAnimal().startsWith(answers.getCharacter())) {
            Animal animal = animalRepository.getAnimalByName(answers.getAnimal());
            if (animal != null && animal.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getCity().equals("") && answers.getCity().startsWith(answers.getCharacter())) {
            City city = cityRepository.getCityByName(answers.getCity());
            if (city != null && city.isActive()) {
                if (cityIsInState != null) {
                    CityIsInState cityIsInStateAnswers = cityIsInStateRepository.getStateOfCity(city.getName(),
                            cityIsInState.getState().getName());
                    if (cityIsInStateAnswers.getState() != null) {
                        answers.setScore(answers.getScore() + extraPoints);
                    }
                }
                if (userData.getCity() != null && city.getName().equals(userData.getCity().getName())) {
                    answers.setScore(answers.getScore() - extraPoints);
                }
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getLake().equals("") && answers.getLake().startsWith(answers.getCharacter())) {
            Lake lake = lakeRepository.getLakeByName(answers.getLake());
            if (lake != null && lake.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getMountain().equals("") && answers.getMountain().startsWith(answers.getCharacter())) {
            Mountain mountain = mountainRepository.getMountainByName(answers.getMountain());
            if (mountain != null && mountain.isActive()) {
                if (cityIsInState != null) {
                    mountainIsInState = mountainIsInStateRepository.getMountainInState(answers.getMountain(),
                            cityIsInState.getState().getName());
                }
                answers.setScore(answers.getScore() + uniqueRightResult);
                if (mountainIsInState != null) {
                    answers.setScore(answers.getScore() + extraPoints);
                }
            }
        }
        if (!answers.getPlant().equals("") && answers.getPlant().startsWith(answers.getCharacter())) {
            Plant plant = plantRepository.getPlantByName(answers.getPlant());
            if (plant != null && plant.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getRiver().equals("") && answers.getRiver().startsWith(answers.getCharacter())) {
            River river = riverRepository.getRiverByName(answers.getRiver());
            if (river != null && river.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
                if (cityIsInState != null) {
                    riverFlowsThroughState = riverFlowsThroughStateRepository.getRiverStateRelation(answers.getRiver(),
                            cityIsInState.getState().getName());
                }
                if (riverFlowsThroughState != null) {
                    answers.setScore(answers.getScore() + extraPoints);
                }
            }
        }
        if (userData.getCity() != null && userData.getCity().getName()!=null && userData.getCity().getName().equals(answers.getCity())) {
            answers.setScore(answers.getScore() + uniqueRightResult);
        }
        checkAndSaveIntoDatabase(answers);
        return answers.getScore();
    }

    @Transactional(readOnly = true)
    @Override
    public String choseLetter() {
        int letterNumber = (int) (Math.random() * 23 + 1);
        return LatinAlfabet.values()[letterNumber].toString();
    }

    @Transactional(readOnly = false)
    public void checkAndSaveIntoDatabase(SubmitAnswersTmp submitAnswersTmp) {
        if (!submitAnswersTmp.getCity().equals("")) {
            if (cityRepository.getCityByName(submitAnswersTmp.getCity()) == null) {
                City city = new City();
                city.setName(submitAnswersTmp.getCity());
                city.setActive(false);
                cityRepository.save(city);
            }
        }
        if (!submitAnswersTmp.getMountain().equals("")) {
            if (mountainRepository.getMountainByName(submitAnswersTmp.getMountain()) == null) {
                Mountain mountain = new Mountain();
                mountain.setName(submitAnswersTmp.getMountain());
                mountain.setActive(false);
                mountainRepository.save(mountain);
            }
        }
        if (!submitAnswersTmp.getAnimal().equals("")) {
            if (animalRepository.getAnimalByName(submitAnswersTmp.getAnimal()) == null) {
                Animal animal = new Animal();
                animal.setName(submitAnswersTmp.getAnimal());
                animal.setActive(false);
                animalRepository.save(animal);
            }
        }
        if (!submitAnswersTmp.getLake().equals("")) {
            if (lakeRepository.getLakeByName(submitAnswersTmp.getLake()) == null) {
                Lake lake = new Lake();
                lake.setName(submitAnswersTmp.getLake());
                lake.setActive(false);
                lakeRepository.save(lake);
            }
        }
        if (!submitAnswersTmp.getPlant().equals("")) {
            if (plantRepository.getPlantByName(submitAnswersTmp.getPlant()) == null) {
                Plant plant = new Plant();
                plant.setName(submitAnswersTmp.getPlant());
                plant.setActive(false);
                plantRepository.save(plant);
            }
        }
        if (!submitAnswersTmp.getRiver().equals("")) {
            if (riverRepository.getRiverByName(submitAnswersTmp.getRiver()) == null) {
                River river = new River();
                river.setName(submitAnswersTmp.getRiver());
                river.setActive(false);
                riverRepository.save(river);
            }
        }
        if (!submitAnswersTmp.getState().equals("")) {
            if (stateRepository.getStateByName(submitAnswersTmp.getState()) == null) {
                State state = new State();
                state.setName(submitAnswersTmp.getState());
                state.setActive(false);
                stateRepository.save(state);
            }
        }
    }

}
