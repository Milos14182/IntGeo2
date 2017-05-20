/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.tasks;

import com.milos.neo4j.services.GameService;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author milos
 */
@Component
public class DeleteOldGames {
    
    @Autowired
    private GameService gameService;
    
    @Scheduled(fixedDelayString = "3600000")
    public void deleteOldGames() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, -1);
        gameService.deleteUserGameScoresBeforeDate(calendar.getTime());
        gameService.deleteOldGames(calendar.getTime());
    }
}
