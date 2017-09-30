/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao.impl;

import com.milos.neo4j.dao.UserDAO;
import com.milos.neo4j.domain.nodes.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author milos
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDAOImpl implements UserDAO {

    @Autowired
    private Session session;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(String username, String firstname, String lastname, String password, String age, String gender, String image, String email, String cityName) {
        String query = "MATCH (u:User)-[r1:LIVES_IN_CITY]->(c1:City),(c2:City)\n"
                + "WHERE u.username = {username} and c2.name={cityName}\n"
                + "CREATE (u)-[r2:LIVES_IN_CITY]->(c2)\n"
                + "SET r2=r1,  u.firstname = {firstname}, u.lastname = {lastname}, u.password = {password}, "
                + "u.age ={age}, u.gender = {gender}, u.userImage = {userImage}, u.email = {email}\n"
                + "delete r1\n"
                + "return u";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("password", password);
        params.put("age", age);
        params.put("gender", gender);
        params.put("userImage", image);
        params.put("email", email);
        params.put("cityName", cityName);

        session.query(query, params, false);
    }

}
