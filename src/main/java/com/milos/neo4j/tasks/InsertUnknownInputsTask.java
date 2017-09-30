/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.tasks;

import com.milos.neo4j.services.UnknownInputsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author milos
 */
@Component
public class InsertUnknownInputsTask {
    
    @Autowired
    private UnknownInputsService inputsService;
    
    //900000
    @Scheduled(fixedDelayString = "10000")
    public void insert() {
        inputsService.saveUnknownIntoDatabase();
    }
}
