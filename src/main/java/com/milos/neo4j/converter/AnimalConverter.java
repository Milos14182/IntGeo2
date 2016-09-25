package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.AnimalData;
import com.milos.neo4j.domain.nodes.Animal;

@Component
public class AnimalConverter extends AbstractConverter<Animal, AnimalData> {

}
