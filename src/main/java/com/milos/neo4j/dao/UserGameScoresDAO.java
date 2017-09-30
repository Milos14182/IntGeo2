/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao;

/**
 *
 * @author milos
 */
public interface UserGameScoresDAO {
    
    void createNewUserGame(Long gameId, String username, Long score);
}
