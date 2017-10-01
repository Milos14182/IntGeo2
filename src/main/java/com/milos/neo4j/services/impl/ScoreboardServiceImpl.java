/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services.impl;

import com.milos.neo4j.dao.ScoreboardDAO;
import com.milos.neo4j.data.Scoreboard;
import com.milos.neo4j.domain.nodes.UserGameScores;
import com.milos.neo4j.repository.UserGameScoresRepository;
import com.milos.neo4j.services.ScoreboardService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @Autowired
    private UserGameScoresRepository userGameScoresRepository;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getDailyScoreboard(Long points) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        Iterable<Scoreboard> scoreboards = scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now, points);
        return scoreboards;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getMontlyScoreboard(Long points) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -1);
        return scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now, points);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Scoreboard> getWeeklyScoreboard(Long points) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return scoreboardDAO.getFullScoreboard(calendar.getTimeInMillis(), now, points);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Scoreboard> getWinningsScoreboard(Long points) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long now = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, -1);
        return scoreboardDAO.getWinningsScoreboard(calendar.getTimeInMillis(), now, points);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<UserGameScores> getAllScoresForUser(String username) {
        return userGameScoresRepository.getAllScoresForUser(username);
    }

}
