package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.domain.nodes.Mountain;

@Component
public class MountainConverter extends AbstractConverter<Mountain, MountainData> {

}
