package com.milos.neo4j.services;

import com.milos.neo4j.data.MountainData;

public interface MountainService {
	public MountainData getMountainByName(String name);
	public void saveMountain(MountainData mountainData);
}
