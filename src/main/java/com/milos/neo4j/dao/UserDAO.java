/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao;

import com.milos.neo4j.domain.nodes.User;

/**
 *
 * @author milos
 */
public interface UserDAO {
    
    void updateUser(String username, String firstname, String lastname, String password, String age,
            String gender, String image, String email, String cityName);
}
