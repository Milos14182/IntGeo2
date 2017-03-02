package com.milos.neo4j.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.converter.MountainConverter;
import com.milos.neo4j.data.MountainData;
import com.milos.neo4j.domain.nodes.Mountain;
import com.milos.neo4j.domain.nodes.State;
import com.milos.neo4j.repository.MountainRepository;
import com.milos.neo4j.repository.StateRepository;
import com.milos.neo4j.services.MountainService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
@Service("mountainService")
public class MountainServiceImpl implements MountainService {

    @Autowired
    MountainRepository mountainRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    MountainConverter mountainConverter;

    @Transactional(readOnly = true)
    @Override
    public MountainData getMountainByName(String name) {
        MountainData mountainData = null;
        Mountain mountain = mountainRepository.getMountainByName(name);
        if (mountain != null) {
            mountain = new Mountain();
            mountainConverter.copyFromEntityToData(mountain, mountainData);
        }
        return mountainData;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveMountain(MountainData mountainData) {
        Mountain mountain = new Mountain();
        mountainConverter.copyFromDataToEntity(mountainData, mountain);
        if (mountainData.getStateData() != null) {
            if (mountainData.getStateData().getId() != null) {
                State state = stateRepository.findOne(mountainData.getStateData().getId());
                mountain.setState(state);
            }
        }
        mountainRepository.save(mountain);
    }

}
