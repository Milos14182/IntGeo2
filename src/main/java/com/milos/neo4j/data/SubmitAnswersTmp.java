package com.milos.neo4j.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("meta")
public class SubmitAnswersTmp {

    @Override
    public String toString() {
        return "{'state' : '" + state + "', 'city' : '" + city + "', 'mountain' : '" + mountain + "', 'river' : '"
                + river + "', 'lake' : '" + lake + "', 'plant' : '" + plant + "', 'animal' : '" + animal
                + "', 'character' : '" + character + "', 'score' : '" + score + "', 'username' : '" + username + "'}";
    }

    private String state;
    private String city;
    private String mountain;
    private String river;
    private String lake;
    private String plant;
    private String animal;
    private String character;
    private Long score;
    private String username;
    private String gameId;
    private Boolean submitet;
    private boolean collectAll;
    private boolean isEnded;

    public SubmitAnswersTmp() {
        super();
    }

    public Boolean getSubmitet() {
        return submitet;
    }

    public void setSubmitet(Boolean submitet) {
        this.submitet = submitet;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getLake() {
        return lake;
    }

    public void setLake(String lake) {
        this.lake = lake;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public boolean isCollectAll() {
        return collectAll;
    }

    public void setCollectAll(boolean collectAll) {
        this.collectAll = collectAll;
    }

    public boolean isIsEnded() {
        return isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

}
