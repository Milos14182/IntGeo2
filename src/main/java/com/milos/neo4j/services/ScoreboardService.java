/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.services;

import com.milos.neo4j.data.Scoreboard;
import com.milos.neo4j.domain.nodes.UserGameScores;
import java.util.List;
import java.util.Set;

/**
 *
 * @author milos
 */
public interface ScoreboardService {
    
    Iterable<Scoreboard> getDailyScoreboard(Long points);
    
    Iterable<Scoreboard> getMontlyScoreboard(Long points);
    
    Iterable<Scoreboard> getWeeklyScoreboard(Long points);
    
    List<Scoreboard> getWinningsScoreboard(Long points);
    
    Set<UserGameScores> getAllScoresForUser(String username);
}
