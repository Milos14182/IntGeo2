package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.domain.nodes.Game;

@Component
public class GameConverter extends AbstractConverter<Game, GameData> {

}
