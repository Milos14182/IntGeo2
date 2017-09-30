/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.domain.relations;

import com.milos.neo4j.domain.nodes.User;
import com.milos.neo4j.domain.nodes.UserGameScores;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author milos
 */
@RelationshipEntity(type = "USER_SCORE_RELATION")
public class UserScores {
    
    @GraphId
    private Long id;
    @StartNode
    private User user;
    @EndNode
    private UserGameScores userGameScores;

    public UserScores() {
    }

    public UserScores(User user, UserGameScores userGameScores) {
        this.user = user;
        this.userGameScores = userGameScores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserGameScores getUserGameScores() {
        return userGameScores;
    }

    public void setUserGameScores(UserGameScores userGameScores) {
        this.userGameScores = userGameScores;
    }
}
