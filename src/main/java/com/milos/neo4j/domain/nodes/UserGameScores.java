package com.milos.neo4j.domain.nodes;

import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class UserGameScores {

    private @GraphId
    Long id;

    @NotNull(message = "Game id input is empty.")
    @Property(name = "gameId")
    private Long gameId;

    @NotNull(message = "Username input is empty.")
    @Property(name = "username")
    private String username;

    @NotNull(message = "Score input is empty.")
    @Property(name = "score")
    private Long score;

    @Relationship(type = "USER_SCORE_RELATION", direction = Relationship.INCOMING)
    private User user;
    
    @Relationship(type = "GAME_SCORE_RELATION", direction = Relationship.INCOMING)
    private Game game;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
