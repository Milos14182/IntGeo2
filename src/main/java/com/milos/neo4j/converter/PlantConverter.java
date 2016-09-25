package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.PlantData;
import com.milos.neo4j.domain.nodes.Plant;

@Component
public class PlantConverter extends AbstractConverter<Plant, PlantData> {

}
