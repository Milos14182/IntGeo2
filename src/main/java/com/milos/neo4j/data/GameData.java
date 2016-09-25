package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("meta")
public class GameData {
	private Long id;
	private Long numberOfPlayers;
	private Set<UserData> players = new HashSet<>();
	private Boolean activeGame;
	private String firstLetter;

	public Boolean getActiveGame() {
		return activeGame;
	}

	public void setActiveGame(Boolean activeGame) {
		this.activeGame = activeGame;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Long numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public Set<UserData> getPlayers() {
		return players;
	}

	public void setPlayers(Set<UserData> players) {
		this.players = players;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

}
