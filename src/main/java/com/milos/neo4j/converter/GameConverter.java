package com.milos.neo4j.converter;

import org.springframework.stereotype.Component;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.domain.nodes.Game;
import java.util.Date;

@Component
public class GameConverter extends AbstractConverter<Game, GameData> {
    
    @Override
    public void copyFromDataToEntity(GameData data, Game entity, String... ignoreProperties) {
        super.copyFromDataToEntity(data, entity, ignoreProperties);
        if (data.getCreationDate() != null) {
            entity.setCreationDate(data.getCreationDate().getTime());
        }
        if (data.getRoundStartDate() != null) {
            entity.setRoundStartDate(data.getRoundStartDate().getTime());
        }
    }
    
    @Override
    public void copyFromEntityToData(Game entity, GameData data, String... ignoreProperties) {
        super.copyFromEntityToData(entity, data, ignoreProperties);
        if (entity.getCreationDate() != null) {
            data.setCreationDate(new Date(entity.getCreationDate()));
        }
        if (entity.getRoundStartDate() != null) {
            data.setRoundStartDate(new Date(entity.getRoundStartDate()));
        }
    }
    
}
