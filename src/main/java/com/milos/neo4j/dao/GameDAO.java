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
public interface GameDAO {
    
    void deleteOldGames();
    
    void endOldGames();
}
