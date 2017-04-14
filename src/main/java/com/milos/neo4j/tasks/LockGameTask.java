/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.tasks;

import com.milos.neo4j.services.GameService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author milos
 */
@Component
public class LockGameTask {
    
    @Autowired
    private GameService gameService;
    
    @Scheduled(fixedDelayString = "5000")
    public void lockUnlockedGames() {        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -1);
        gameService.lockStartedGames(calendar.getTime());
    }
}
