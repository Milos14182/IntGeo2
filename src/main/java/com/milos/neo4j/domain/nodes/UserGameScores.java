package com.milos.neo4j.domain.nodes;

import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserGameScores {
	private @GraphId Long id;

	@NotNull(message = "Game id input is empty.")
	@Property(name = "gameId")
	private Long gameId;

	@NotNull(message = "Username input is empty.")
	@Property(name = "username")
	private String username;
	
	@NotNull(message = "Score input is empty.")
	@Property(name = "score")
	private Long score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
	
	
}
