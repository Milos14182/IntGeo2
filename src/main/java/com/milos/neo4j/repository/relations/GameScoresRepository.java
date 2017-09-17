/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.repository.relations;

import com.milos.neo4j.domain.relations.GameScores;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author milos
 */
public interface GameScoresRepository extends CrudRepository<GameScores, Long> {
    
}
