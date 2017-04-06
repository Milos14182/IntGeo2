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

    @Property(name = "activeGame")
    private Boolean activeGame;

    @Relationship(type = "GAME_RELATION", direction = Relationship.OUTGOING)
    private Set<User> players = new HashSet<>();

    @Property(name = "firstLetter")
    private String firstLetter;

    @Property(name = "creationDate")
    private Date creationDate;
    
    @Property(name = "locked")
    private Boolean locked;

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
    
}
