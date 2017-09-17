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
        Date creationDate = data.getCreationDate() != null ? data.getCreationDate() : new Date();
        Date roundStartDate = data.getRoundStartDate() != null ? data.getCreationDate() : new Date();
        entity.setCreationDate(creationDate.getTime());
        entity.setRoundStartDate(roundStartDate.getTime());
    }

    @Override
    public void copyFromEntityToData(Game entity, GameData data, String... ignoreProperties) {
        super.copyFromEntityToData(entity, data, ignoreProperties);
        Date creationDate = entity.getCreationDate() != null ? new Date(entity.getCreationDate()) : null;
        Date roundStartDate = entity.getRoundStartDate() != null ? new Date(entity.getRoundStartDate()) : null;
        data.setCreationDate(creationDate);
        data.setRoundStartDate(roundStartDate);
    }

}
