package com.milos.neo4j.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.roundbroker.RoundBroker;
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.UserService;
import java.util.Map;

@Controller
public class RoundController {
    // @Autowired
    // PlayService playService;

    @Autowired
    UserService userService;

    @Autowired
    RoundBroker roundBroker;

    @Autowired
    GameService gameService;

    private HashMap<String, List<SubmitAnswersTmp>> gamesContainer = null;

    public RoundController() {
        this.gamesContainer = new HashMap<>();
    }

    @MessageMapping("/play/initialization/{gameId}")
    public Integer checkInitiationTime(@DestinationVariable Long gameId) {
        return roundBroker.waitUsersToJoinGame(gameId);
    }

    @MessageMapping("/play/checkLocked/{gameId}")
    public Boolean checkLocked(@DestinationVariable Long gameId) {
        return roundBroker.gameCanStart(gameId);
    }

    @MessageMapping("/play/roundSync/{gameId}")
    public Integer rooundSync(@DestinationVariable Long gameId) {
        return roundBroker.gameRoundSinc(gameId);
    }

    @MessageMapping("/play/answers/{gameId}")
    public String sendToGame(String answers, @DestinationVariable Integer gameId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SubmitAnswersTmp answersTmp = objectMapper.readValue(answers, SubmitAnswersTmp.class);
        GameData gameData = gameService.getGameById(Long.valueOf(answersTmp.getGameId()));
        List<SubmitAnswersTmp> games = gamesContainer.get(answersTmp.getGameId());
        UserData userData = userService.getUser(answersTmp.getUsername());
        Long scorePerRound = gameData.getEndPoints().longValue();
        Boolean isEnded = gameData.getEnded();        
        Boolean countIsEnded = Boolean.FALSE;
        if (gameData.getEnded().compareTo(Boolean.FALSE) == 0) {
            Map<String, Object> map = roundBroker.countScore(answersTmp, userData, gameData);
            scorePerRound = (Long) map.get("score");
            countIsEnded = (Boolean) map.get("isEnded");
        }
        if (roundBroker.getInitalLetter() && !isEnded) {
            roundBroker.createLetterForGameRound(gameId.longValue());
        }
        answersTmp.setCharacter(roundBroker.getLetter());
        answersTmp.setScore(scorePerRound);
        answersTmp.setSubmitet(true);
        if (games != null) {
            SubmitAnswersTmp findGame = null;
            for (SubmitAnswersTmp submitAnswersTmp : games) {
                if (submitAnswersTmp.getUsername().equals(answersTmp.getUsername())) {
                    setNewAnswers(submitAnswersTmp, answersTmp, scorePerRound, countIsEnded);
                    findGame = submitAnswersTmp;
                }
            }
            if (findGame == null) {
                games.add(answersTmp);
                gamesContainer.put(answersTmp.getGameId(), games);
            }
        } else {
            games = new ArrayList<>();
            games.add(answersTmp);
            gamesContainer.put(answersTmp.getGameId(), games);
        }
        if (!isEnded) {
            gameService.updateUserGame(answersTmp.getUsername(), gameId.longValue(), scorePerRound);
        }
        if (!roundBroker.waitForAllUsersToAnswer(games, gameId.longValue(), answersTmp.isCollectAll())) {
            return objectMapper.writeValueAsString(getSubmitedUsers(games));
        }
        for (SubmitAnswersTmp game : games) {
            game.setCharacter(roundBroker.getLetter());
        }

        return objectMapper.writeValueAsString(games);
    }

    private void setNewAnswers(SubmitAnswersTmp submitAnswersTmp, SubmitAnswersTmp answersTmp, Long score, boolean isEnded) {
        submitAnswersTmp.setCharacter(roundBroker.getLetter());
        submitAnswersTmp.setScore(score);
        submitAnswersTmp.setSubmitet(true);
        submitAnswersTmp.setAnimal(answersTmp.getAnimal());
        submitAnswersTmp.setCity(answersTmp.getCity());
        submitAnswersTmp.setLake(answersTmp.getLake());
        submitAnswersTmp.setMountain(answersTmp.getMountain());
        submitAnswersTmp.setPlant(answersTmp.getPlant());
        submitAnswersTmp.setRiver(answersTmp.getRiver());
        submitAnswersTmp.setState(answersTmp.getState());
        submitAnswersTmp.setIsEnded(isEnded);
    }

    private Map<String, Boolean> getSubmitedUsers(List<SubmitAnswersTmp> answers) {
        Map<String, Boolean> users = new HashMap<>();
        users.put("submitted", Boolean.TRUE);
        answers.forEach((submitAnswersTmp) -> {
            if (submitAnswersTmp.getSubmitet().equals(Boolean.TRUE)) {
                users.put(submitAnswersTmp.getUsername(), Boolean.TRUE);
            }
        });
        return users;
    }
}
