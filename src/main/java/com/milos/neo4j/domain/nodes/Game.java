package com.milos.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    private @GraphId
    Long id;

    @Property(name = "numberOfPlayers")
    private Long numberOfPlayers;

    @Relationship(type = "GAME_RELATION", direction = Relationship.OUTGOING)
    private Set<User> players = new HashSet<>();

    @Property(name = "firstLetter")
    private String firstLetter;

    @Property(name = "creationDate")
    private Long creationDate;

    @Property(name = "locked")
    private Boolean locked;

    @Property(name = "roundStartDate")
    private Long roundStartDate;

    @Property(name = "currentRound")
    private Integer currentRoundNumber;
    
    @Property(name = "previouslySelectedLetters")
    private String previouslySelectedLetters;
    
    @Property(name = "ended")
    private Boolean ended;

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

    public Set<User> getPlayers() {
        return players;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getRoundStartDate() {
        return roundStartDate;
    }

    public void setRoundStartDate(Long roundStartDate) {
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

}
