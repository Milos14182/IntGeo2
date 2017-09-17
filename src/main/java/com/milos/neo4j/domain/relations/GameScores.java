/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.domain.relations;

import com.milos.neo4j.domain.nodes.Game;
import com.milos.neo4j.domain.nodes.UserGameScores;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author milos
 */
@RelationshipEntity(type = "GAME_SCORE_RELATION")
public class GameScores {
    
    @GraphId
    private Long id;
    @StartNode
    private Game game;
    @EndNode
    private UserGameScores userGameScores;

    public GameScores() {
    }

    public GameScores(Game game, UserGameScores userGameScores) {
        this.game = game;
        this.userGameScores = userGameScores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public UserGameScores getUserGameScores() {
        return userGameScores;
    }

    public void setUserGameScores(UserGameScores userGameScores) {
        this.userGameScores = userGameScores;
    }
}
