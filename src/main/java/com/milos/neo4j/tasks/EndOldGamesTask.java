/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.tasks;

import com.milos.neo4j.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author milos
 */
@Component
public class EndOldGamesTask {
    
    @Autowired
    private GameService gameService;
    
    @Scheduled(fixedDelayString = "3600000")
    public void endOldGames() {
        gameService.endOldGames();
    }
}
