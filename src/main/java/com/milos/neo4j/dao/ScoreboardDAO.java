/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao;

import com.milos.neo4j.data.Scoreboard;
import java.util.List;

/**
 *
 * @author milos
 */
public interface ScoreboardDAO {
    
    Iterable<Scoreboard> getFullScoreboard(Long startTime, Long endTime);
    
    List<Scoreboard> getWinningsScoreboard(Long startTime, Long endTime);
    
    List<Scoreboard> getAllScoresForUser(String username);
}
