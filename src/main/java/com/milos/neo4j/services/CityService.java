package com.milos.neo4j.services;

import com.milos.neo4j.data.CityData;

public interface CityService {
	public Iterable<CityData> findAllCitys();
	public CityData getCityByName(String name);
	public void saveCity(CityData cityData);
}
