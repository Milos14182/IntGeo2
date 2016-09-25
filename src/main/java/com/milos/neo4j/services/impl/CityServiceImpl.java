package com.milos.neo4j.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.CityConverter;
import com.milos.neo4j.data.CityData;
import com.milos.neo4j.domain.nodes.City;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.CityRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.repository.relations.CityIsInStateRepository;
import com.milos.neo4j.services.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CityIsInStateRepository cityIsInStateRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityConverter cityConverter;
	
	public Iterable<CityData> findAllCitys() {
		Set<City> citys = cityRepository.getAllCities();
		Set<CityData> cityDatas = new HashSet<CityData>();
		CityData cityData = new CityData();
		cityConverter.copyFromEntitySetToDataSet(citys, cityDatas, cityData);
		List<CityData> sortedCityes = new ArrayList<>(cityDatas);
		Collections.sort(sortedCityes, (a, b) -> b.getName().compareTo(a.getName()));
		return sortedCityes;
	}

	public CityData getCityByName(String name) {
		CityData cityData = null;
		City city = cityRepository.getCityByName(name);
		if (city != null) {
			cityData = new CityData();
			cityConverter.copyFromEntityToData(city, cityData);
		}
		return cityData;
	}

	@Override
	public void saveCity(CityData cityData) {
		City city = new City();
		cityConverter.copyFromDataToEntity(cityData, city);
		if (cityData.getStateData()!=null) {
			if (cityData.getStateData().getId()!=null) {
				State state = stateRepository.findOne(cityData.getStateData().getId());
				city.setState(state);
			}
		}
		cityRepository.save(city);
	}

}
