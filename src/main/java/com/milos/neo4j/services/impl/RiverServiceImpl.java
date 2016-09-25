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

@Service("riverService")
public class RiverServiceImpl implements RiverService {
	@Autowired
	RiverRepository riverRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	RiverConverter riverConverter;
	
	public RiverData getRiverByName(String name) {
		RiverData riverData = null;
		River river = riverRepository.getRiverByName(name);
		if (river!=null) {
			riverData = new RiverData();
			riverConverter.copyFromEntityToData(river, riverData);
		}
		return riverData;
	}

	@Override
	public void saveRiver(RiverData riverData) {
		River river = new River();
		riverConverter.copyFromDataToEntity(riverData, river);
		Set<State> states = new HashSet<>();
		if (!riverData.getStateIds().isEmpty()) {
			for (Long stateId : riverData.getStateIds()) {
				State state = stateRepository.findOne(stateId);
				states.add(state);
			}
			river.setState(states);
		}
		riverRepository.save(river);
	}

}
