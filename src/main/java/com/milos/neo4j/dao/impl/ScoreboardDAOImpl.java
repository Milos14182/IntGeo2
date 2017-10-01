/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao.impl;

import com.milos.neo4j.dao.ScoreboardDAO;
import com.milos.neo4j.data.Scoreboard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
public class ScoreboardDAOImpl implements ScoreboardDAO {

    @Autowired
    private Session session;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getFullScoreboard(Long startTime, Long endTime, Long points) {
        String getScorboards = "MATCH (g:Game)--(n:UserGameScores)--(u:User)--(c:City) \n"                
                + "where g.creationDate > {startTime} and g.creationDate < {endTime} \n"
                + "and g.ended = true and n.score >= g.endPoints and g.endPoints = {points} \n"
                + "with sum(n.score) as score, n.username as username, u.firstname as firstname, \n"
                + "u.lastname as lastname, u.userImage as userImage, c.name as city, g.ended as ended \n"
                + "return username, firstname, lastname, city, score, userImage order by score desc";
        Map<String, Long> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("points", points);

        Result result = session.query(getScorboards, params, true);
        return new LinkedList<>(convertResultToList(result));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Scoreboard> getWinningsScoreboard(Long startTime, Long endTime, Long points) {
        String getScorboards = "MATCH (g:Game)--(n:UserGameScores)--(u:User)--(c:City) \n"                
                + "where g.creationDate > {startTime} and g.creationDate < {endTime} \n"
                + "and g.ended = true and n.score >= g.endPoints and g.endPoints = {points} \n"
                + "with count(g) as score, n.username as username, u.firstname as firstname, \n"
                + "u.lastname as lastname, u.userImage as userImage, c.name as city, g.ended as ended \n"
                + "return username, firstname, lastname, city, score, userImage order by score desc";
        Map<String, Long> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("points", points);

        Result result = session.query(getScorboards, params, true);
        return new LinkedList<>(convertResultToList(result));
    }

    private List<Scoreboard> convertResultToList(Result result) {
        List<Scoreboard> scoreboards = new ArrayList<>();
        result.forEach((Map<String, Object> t) -> {
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.setUsername((String) t.get("username"));
            scoreboard.setFirstname((String) t.get("firstname"));
            scoreboard.setLastname((String) t.get("lastname"));
            scoreboard.setGameId((Integer) t.get("gameId"));
            Integer score = (Integer) t.get("score");
            scoreboard.setScore(score != null ? score : 0);
            scoreboard.setCity((String) t.get("city"));
            scoreboard.setUserImage((String) t.get("userImage"));
            scoreboards.add(scoreboard);
        });
        return scoreboards;
    }

}
