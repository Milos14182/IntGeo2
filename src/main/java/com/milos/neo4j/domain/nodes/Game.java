package com.milos.neo4j.domain.nodes;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Game {

    private @GraphId
    Long id;

    @NotNull
    @Property(name = "numberOfPlayers")
    private Long numberOfPlayers;

    @NotNull
    @Relationship(type = "GAME_RELATION", direction = Relationship.OUTGOING)
    private Set<User> players = new HashSet<>();

    @NotNull
    @Property(name = "firstLetter")
    private String firstLetter;

    @NotNull
    @Property(name = "creationDate")
    private Long creationDate;

    @NotNull
    @Property(name = "locked")
    private Boolean locked;

    @NotNull
    @Property(name = "roundStartDate")
    private Long roundStartDate;

    @NotNull
    @Property(name = "currentRound")
    private Integer currentRoundNumber;

    @NotNull
    @Property(name = "previouslySelectedLetters")
    private String previouslySelectedLetters;

    @NotNull
    @Property(name = "ended")
    private Boolean ended;

    @NotNull
    @Property(name = "endPoints")
    private Integer endPoints;

    @Relationship(type = "GAME_SCORE_RELATION", direction = Relationship.OUTGOING)
    private Set<UserGameScores> userGameScores = new HashSet<>();

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

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public Set<UserGameScores> getUserGameScores() {
        return userGameScores;
    }

    public void setUserGameScores(Set<UserGameScores> userGameScores) {
        this.userGameScores = userGameScores;
    }

    public Integer getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(Integer endPoints) {
        this.endPoints = endPoints;
    }
}
