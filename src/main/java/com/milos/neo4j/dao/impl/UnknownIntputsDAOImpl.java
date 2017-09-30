/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.dao.impl;

import com.milos.neo4j.dao.UnknownInputsDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
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
public class UnknownIntputsDAOImpl implements UnknownInputsDAO {

    @Autowired
    private Session session;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public int saveUnknownIntoDatabase(List<String> queries, List<String> names) {
        int i = 0;
        try {
            for (i=0; i < queries.size(); i++) {
                Map<String, String> params = new HashMap<>();
                params.put("name", names.get(i));
                session.query(queries.get(i), params, false);
            }
        } catch (RuntimeException ex) {
            return i;
        }
        return i;
    }

}
