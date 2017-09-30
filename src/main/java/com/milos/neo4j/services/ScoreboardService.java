/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services;

import com.milos.neo4j.data.Scoreboard;
import java.util.List;

/**
 *
 * @author milos
 */
public interface ScoreboardService {
    
    Iterable<Scoreboard> getFullScoreboard();
    
    Iterable<Scoreboard> getMontlyScoreboard();
    
    Iterable<Scoreboard> getWeeklyScoreboard();
    
    List<Scoreboard> getWinningsScoreboard();
    
    List<Scoreboard> getAllScoresForUser(String username);
}
