package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.LakeData;
import com.milos.neo4j.domain.nodes.Lake;

@Component
public class LakeConverter extends AbstractConverter<Lake, LakeData> {

}
