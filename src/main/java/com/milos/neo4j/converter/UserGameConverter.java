package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.UserGameData;
import com.milos.neo4j.domain.nodes.UserGameScores;

@Component
public class UserGameConverter extends AbstractConverter<UserGameScores, UserGameData> {

}
