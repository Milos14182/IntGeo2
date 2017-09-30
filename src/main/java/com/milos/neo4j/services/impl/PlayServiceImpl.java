package com.milos.neo4j.services.impl;

import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;
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
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.PlayService;
import com.milos.neo4j.services.UnknownInputsService;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Service("playService")
public class PlayServiceImpl implements PlayService {

    private static final long uniqueRightResult = 10;
    private static final long extraPoints = 3;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private RiverRepository riverRepository;
    @Autowired
    private MountainRepository mountainRepository;
    @Autowired
    private LakeRepository lakeRepository;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private MountainIsInStateRepository mountainIsInStateRepository;
    @Autowired
    private CityIsInStateRepository cityIsInStateRepository;
    @Autowired
    private RiverFlowsThroughStateRepository riverFlowsThroughStateRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private UnknownInputsService unknownInputsService;

    private Map<Integer, String> letters = generateLetterMap();

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
            State state = stateRepository.getStateByNameOrSynonim(answers.getState(), answers.getState() + ",");
            if (state == null) {
                unknownInputsService.getStates().put(answers.getState(), new StateData(answers.getState()));
            } else if (state.isActive()) {
                if (userData.getCity() != null) {
                    cityIsInState = cityIsInStateRepository.getStateOfCity(userData.getCity().getName(),
                            state.getName());
                }
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getAnimal().equals("") && answers.getAnimal().startsWith(answers.getCharacter())) {
            Animal animal = animalRepository.getAnimalByNameOrSynonim(answers.getAnimal(), answers.getAnimal() + ",");
            if (animal == null) {
                unknownInputsService.getAnimals().put(answers.getAnimal(), new AnimalData(answers.getAnimal()));
            } else if (animal.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getCity().equals("") && answers.getCity().startsWith(answers.getCharacter())) {
            City city = cityRepository.getCityByNameOrSynonim(answers.getCity(), answers.getCity() + ",");
            if (city == null) {
                unknownInputsService.getCitys().put(answers.getCity(), new CityData(answers.getCity()));
            } else if (city.isActive()) {
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
            Lake lake = lakeRepository.getLakeByNameOrSynonim(answers.getLake(), answers.getLake() + ",");
            if (lake == null) {
                unknownInputsService.getLakes().put(answers.getLake(), new LakeData(answers.getLake()));
            } else if (lake.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getMountain().equals("") && answers.getMountain().startsWith(answers.getCharacter())) {
            Mountain mountain = mountainRepository.getMountainByNameOrSynonim(answers.getMountain(), answers.getMountain() + ",");
            if (mountain == null) {
                unknownInputsService.getMountains().put(answers.getMountain(), new MountainData(answers.getMountain()));
            } else if (mountain.isActive()) {
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
            Plant plant = plantRepository.getPlantByNameOrSynonim(answers.getPlant(), answers.getPlant() + ",");
            if (plant == null) {
                unknownInputsService.getPlants().put(answers.getPlant(), new PlantData(answers.getPlant()));
            } else if (plant.isActive()) {
                answers.setScore(answers.getScore() + uniqueRightResult);
            }
        }
        if (!answers.getRiver().equals("") && answers.getRiver().startsWith(answers.getCharacter())) {
            River river = riverRepository.getRiverByNameOrSynonim(answers.getRiver(), answers.getRiver() + ",");
            if (river == null) {
                unknownInputsService.getRivers().put(answers.getRiver(), new RiverData(answers.getRiver()));
            } else if (river.isActive()) {
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
        if (userData.getCity() != null && userData.getCity().getName() != null && userData.getCity().getName().equals(answers.getCity())) {
            answers.setScore(answers.getScore() + uniqueRightResult);
        }
        return answers.getScore();
    }

    @Transactional(readOnly = true)
    @Override
    public String choseLetter(Long gameId) {
        String prevouslySelectedLetters = gameService.getPreviousLetters(gameId);
        int count = prevouslySelectedLetters.split(",").length;
        boolean selected = false;
        String letter = "";
        while (!selected) {
            Random r = new Random();
            int result = r.nextInt(24 - 0) + 0;
            letter = letters.get(result);
            if (!prevouslySelectedLetters.contains(letter) || count > 20) {
                selected = true;
            }
        }
        prevouslySelectedLetters += ", " + letter;
        gameService.updatePreviousLetters(gameId, prevouslySelectedLetters);
        return letter;
    }

    @Transactional(readOnly = true)
    private Map<Integer, String> generateLetterMap() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            String letter = LatinAlfabet.values()[i].toString();
            map.put(i, letter);
        }
        return map;
    }

}
