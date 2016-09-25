package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.CityData;
import com.milos.neo4j.domain.nodes.City;

@Component
public class CityConverter extends AbstractConverter<City, CityData> {

}
