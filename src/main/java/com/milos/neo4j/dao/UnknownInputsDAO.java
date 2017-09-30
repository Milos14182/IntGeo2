/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao;

import java.util.List;

/**
 *
 * @author milos
 */
public interface UnknownInputsDAO {
    
    public int saveUnknownIntoDatabase(List<String> queries, List<String> names);
}
