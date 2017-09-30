/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao.impl;

import com.milos.neo4j.dao.UserGameScoresDAO;
import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.UserGameScores;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author milos
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserGameScoresDAOImpl implements UserGameScoresDAO{

    @Autowired
    private Session session;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void createNewUserGame(Long gameId, String username, Long score) {
        String query = "MATCH (g:Game),(u:User) where ID(g)={gameId} and u.username = {username} "
                + "CREATE (g)<-[r:GAME_SCORE_RELATION]-(ug:UserGameScores {gameId : {gameId}, username: {username}, score: {score}})-[y:USER_SCORE_RELATION]->(u) "
                + "return g,u,ug,r,y";
        Map<String, Object> params = new HashMap<>();
        params.put("gameId", gameId);
        params.put("username", username);
        params.put("score", score);
        session.query(query, params, false);       
    }
}
