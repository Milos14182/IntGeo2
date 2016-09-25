package com.milos.neo4j.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.StateConverter;
import com.milos.neo4j.data.StateData;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.services.StateService;

@Service("stateService")
public class StateServiceImpl implements StateService {
	@Autowired
	StateRepository stateRepository;

	@Autowired
	StateConverter stateConverter;

	public StateData getStateByName(String name) {
		StateData stateData = null;
		State state = stateRepository.getStateByName(name);
		if (state != null) {
			stateData = new StateData();
			stateConverter.copyFromEntityToData(state, stateData);	
		}
		return stateData;
	}

	@Override
	public Iterable<StateData> getAllStates() {
		Set<State> states = (Set<State>) stateRepository.findAll();
		Set<StateData> stateDatas = new HashSet<>();
		stateConverter.copyFromEntitySetToDataSet(states, stateDatas, new StateData());
		List<StateData> sortedStateDatas = new ArrayList<>(stateDatas);
		Collections.sort(sortedStateDatas, (a, b) -> b.getName().compareTo(a.getName()));
		return sortedStateDatas;
	}

	@Override
	public void saveState(StateData stateData) {
		State state = new State();
		stateConverter.copyFromDataToEntity(stateData, state);
		stateRepository.save(state);		
	}

}
