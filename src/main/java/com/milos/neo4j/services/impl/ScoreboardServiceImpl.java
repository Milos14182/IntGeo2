/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services.impl;

import com.milos.neo4j.dao.ScoreboardDAO;
import com.milos.neo4j.data.Scoreboard;
import com.milos.neo4j.services.ScoreboardService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author milos
 */
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class ScoreboardServiceImpl implements ScoreboardService {
    
    @Autowired
    private ScoreboardDAO scoreboardDAO;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getFullScoreboard() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        Iterable<Scoreboard> scoreboards = scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now);
        return scoreboards;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getMontlyScoreboard() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -1);
        return scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getWeeklyScoreboard() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Scoreboard> getWinningsScoreboard() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -1);
        return scoreboardDAO.getWinningsScoreboard(calendar.getTimeInMillis(), now);
    }

}
