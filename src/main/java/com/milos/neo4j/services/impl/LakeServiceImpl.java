package com.milos.neo4j.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.LakeConverter;
import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.domain.nodes.Lake;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.LakeRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.services.LakeService;

@Service("lakeService")
public class LakeServiceImpl implements LakeService {
	@Autowired
	LakeRepository lakeRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	LakeConverter lakeConverter; 
	
	public LakeData getLakeByName(String name) {
		LakeData lakeData = null;
		Lake lake = lakeRepository.getLakeByName(name);
		if (lake != null) {
			lakeData = new LakeData();
			lakeConverter.copyFromEntityToData(lake, lakeData);
		}
		return lakeData;
	}

	@Override
	public void saveLake(LakeData lakeData) {
		Lake lake = new Lake();
		lakeConverter.copyFromDataToEntity(lakeData, lake);
		if (lakeData.getStateData()!=null) {
			if (lakeData.getStateData().getId()!=null) {
				State state = stateRepository.findOne(lakeData.getStateData().getId());
				lake.setState(state);
			}
		}
		lakeRepository.save(lake);
	}
	
}
