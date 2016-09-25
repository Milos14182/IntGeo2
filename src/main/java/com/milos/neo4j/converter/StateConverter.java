package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.StateData;
import com.milos.neo4j.domain.nodes.State;

@Component
public class StateConverter extends AbstractConverter<State, StateData> {

}
