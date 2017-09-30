/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services.impl;

import com.milos.neo4j.dao.UnknownInputsDAO;
import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.data.StateData;
import com.milos.neo4j.services.UnknownInputsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author milos
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UnknownInputsServiceImpl implements UnknownInputsService {

    @Autowired
    private UnknownInputsDAO unknownInputsDAO;

    private Map<String, StateData> states = null;
    private Map<String, MountainData> mountains = null;
    private Map<String, RiverData> rivers = null;
    private Map<String, CityData> citys = null;
    private Map<String, LakeData> lakes = null;
    private Map<String, AnimalData> animals = null;
    private Map<String, PlantData> plants = null;

    @PostConstruct
    public void postConstruct() {
        states = new HashMap<>();
        mountains = new HashMap<>();
        rivers = new HashMap<>();
        citys = new HashMap<>();
        lakes = new HashMap<>();
        animals = new HashMap<>();
        plants = new HashMap<>();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveUnknownIntoDatabase() {
        List<String> names = new ArrayList<>();
        List<String> queryList = buildList(names);
        int i = unknownInputsDAO.saveUnknownIntoDatabase(queryList, names);
        emptyMaps(names, i);
    }

    private List<String> buildList(List<String> names) {
        List<String> list = new ArrayList<>();
        states.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:State{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        lakes.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:Lake{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        mountains.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:Mountain{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        rivers.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:River{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        citys.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:City{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        animals.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:Animal{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        plants.entrySet().forEach((entry) -> {
            if (entry.getValue().getName() != null && !entry.getValue().getName().equals("")) {
                list.add("CREATE (n:Plant{name: '" + entry.getValue().getName() + "', active: " + entry.getValue().isActive() + "}) return n");
                names.add(entry.getValue().getName());
            }
        });
        return list;
    }

    private void emptyMaps(List<String> names, int i) {
        for (int j=0; j<i;j++) {
            states.remove(names.get(j));
            mountains.remove(names.get(j));
            rivers.remove(names.get(j));
            citys.remove(names.get(j));
            lakes.remove(names.get(j));
            animals.remove(names.get(j));
            plants.remove(names.get(j));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, StateData> getStates() {
        return states;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, MountainData> getMountains() {
        return mountains;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, RiverData> getRivers() {
        return rivers;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, CityData> getCitys() {
        return citys;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, LakeData> getLakes() {
        return lakes;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, AnimalData> getAnimals() {
        return animals;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, PlantData> getPlants() {
        return plants;
    }
}
