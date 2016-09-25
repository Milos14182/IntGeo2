package com.milos.neo4j.services;

import com.milos.neo4j.data.StateData;

public interface StateService {
	public StateData getStateByName(String name);
	public Iterable<StateData> getAllStates();
	public void saveState (StateData stateData);
}
