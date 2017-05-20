package com.milos.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.milos.neo4j.domain.nodes.UserGameScores;

public interface UserGameScoresRepository extends GraphRepository<UserGameScores> {
	@Query("MATCH (u:UserGameScores) WHERE u.username = {0} and u.gameId = {1} RETURN u")
	public UserGameScores getUserGameScore(String username, Long id);
	
	@Query("MATCH (u:UserGameScores) WHERE u.username = {0} and u.gameId = {1} SET u.score={2} RETURN u")
	public UserGameScores updateUserGameScore(String username, Long id, Long score);
        
        @Query("MATCH (u:UserGameScores) WHERE u.gameId = {0} DELETE u")
	public void deleteUserGameScore(Long id);
}
