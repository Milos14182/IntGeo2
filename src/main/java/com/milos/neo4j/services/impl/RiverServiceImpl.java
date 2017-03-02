package com.milos.neo4j.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.RiverConverter;
import com.milos.neo4j.data.RiverData;
import com.milos.neo4j.domain.nodes.River;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.RiverRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.services.RiverService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Service("riverService")
public class RiverServiceImpl implements RiverService {

    @Autowired
    RiverRepository riverRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    RiverConverter riverConverter;

    @Transactional(readOnly = true)
    @Override
    public RiverData getRiverByName(String name) {
        RiverData riverData = null;
        River river = riverRepository.getRiverByName(name);
        if (river != null) {
            riverData = new RiverData();
            riverConverter.copyFromEntityToData(river, riverData);
        }
        return riverData;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveRiver(RiverData riverData) {
        River river = new River();
        riverConverter.copyFromDataToEntity(riverData, river);
        Set<State> states = new HashSet<>();
        if (!riverData.getStateIds().isEmpty()) {
            riverData.getStateIds().stream()
                    .map((stateId) -> stateRepository.findOne(stateId))
                    .forEach((state) -> {
                        states.add(state);
                    });
        }
        river.setState(states);
        riverRepository.save(river);
    }

}
