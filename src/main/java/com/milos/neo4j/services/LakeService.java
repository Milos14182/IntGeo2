package com.milos.neo4j.services;

import com.milos.neo4j.data.LakeData;

public interface LakeService {
	public LakeData getLakeByName(String name);
	public void saveLake(LakeData lakeData);
}
