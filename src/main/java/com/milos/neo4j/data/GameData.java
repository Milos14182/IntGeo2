package com.milos.neo4j.data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties("meta")
public class GameData {

    private Long id;
    private Long numberOfPlayers;
    private Set<UserData> players = new HashSet<>();
    private String firstLetter;
    private Date creationDate;
    private Boolean locked;
    private Date roundStartDate;
    private Integer currentRoundNumber;
    private String previouslySelectedLetters;
    private Boolean ended;
    private Integer endPoints;
    
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getRoundStartDate() {
        return roundStartDate;
    }

    public void setRoundStartDate(Date roundStartDate) {
        this.roundStartDate = roundStartDate;
    }

    public Integer getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber(Integer currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    public String getPreviouslySelectedLetters() {
        return previouslySelectedLetters;
    }

    public void setPreviouslySelectedLetters(String previouslySelectedLetters) {
        this.previouslySelectedLetters = previouslySelectedLetters;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public Integer getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(Integer endPoints) {
        this.endPoints = endPoints;
    }
    
}
