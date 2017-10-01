/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao.impl;

import com.milos.neo4j.dao.GameDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
public class GameDAOImpl implements GameDAO {

    @Autowired
    private Session session;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteOldGames() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        String deleteQuery = "MATCH (n:UserGameScores)--(g:Game) where g.creationDate <= {date} detach delete n, g";
        Map<String, Long> params = new HashMap<>();
        params.put("date", calendar.getTimeInMillis());
        session.query(deleteQuery, params, false);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void endOldGames() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        String updateQuery = "MATCH (g:Game) where g.ended = false and g.roundStartDate <= {date} set g.ended = true";
        Map<String, Long> params = new HashMap<>();
        params.put("date", calendar.getTimeInMillis());
        session.query(updateQuery, params, false);
    }

}
