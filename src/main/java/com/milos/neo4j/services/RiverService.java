package com.milos.neo4j.services;

import com.milos.neo4j.data.RiverData;

public interface RiverService {
	public RiverData getRiverByName(String name);
	public void saveRiver(RiverData riverData);
}
