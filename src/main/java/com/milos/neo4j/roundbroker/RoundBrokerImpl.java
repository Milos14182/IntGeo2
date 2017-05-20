package com.milos.neo4j.roundbroker;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.PlayService;
import com.milos.neo4j.services.UserService;
import java.util.Calendar;

@Service
public class RoundBrokerImpl implements RoundBroker {

    @Autowired
    PlayService playService;

    @Autowired
    GameService gameService;

    @Autowired
    private UserService userService;

    String letter = "";
    Boolean initalLetter = true;

    @Override
    public String createLetterForGameRound(Long gameId) {
        initalLetter = false;
        letter = playService.choseLetter(gameId);
        return letter;
    }

    @Override
    public boolean waitForAllUsersToAnswer(List<SubmitAnswersTmp> answers, Long gameId, boolean collectAll) {
        GameData gameData = gameService.getGameById(gameId);
        for (SubmitAnswersTmp submitAnswersTmp : answers) {
            if ((!submitAnswersTmp.getSubmitet() || answers.size() != gameData.getNumberOfPlayers().intValue())
                    && !collectAll) {
                return false;
            }
        }
        for (SubmitAnswersTmp submitAnswersTmp : answers) {
            submitAnswersTmp.setSubmitet(false);
        }
        letter = playService.choseLetter(gameId);
        Date roundDate = new Date();
        gameService.updateGameLetter(letter, gameId, gameData.getCurrentRoundNumber() + 1, roundDate.getTime());
        return true;
    }

    @Override
    public boolean checkTime(Date roundStartTime) {
        @SuppressWarnings("deprecation")
        int minutes = roundStartTime.getMinutes();
        @SuppressWarnings("deprecation")
        int seconds = roundStartTime.getSeconds();
        Date currentDate = new Date();
        @SuppressWarnings("deprecation")
        int currentSecconds = currentDate.getSeconds();
        @SuppressWarnings("deprecation")
        int currentMinutes = currentDate.getMinutes();
        if ((minutes == (currentMinutes - 1)) && (seconds == currentSecconds)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getLetter() {
        return letter;
    }

    @Override
    public Boolean getInitalLetter() {
        return initalLetter;
    }

    @Override
    public Long countScore(SubmitAnswersTmp submitAnswersTmp, UserData userData) {
        Long score = playService.countScore(submitAnswersTmp, userData);
        if (score.compareTo(Long.valueOf(300))>=0) {
            gameService.endGame(Long.valueOf(submitAnswersTmp.getGameId()));
        }        
        return score;
    }

    @Override
    public Boolean gameCanStart(Long gameId) {
        return gameService.checkIsLocked(gameId);
    }

    @Override
    public Integer waitUsersToJoinGame(Long gameId) {
        GameData gameData = gameService.getGameById(gameId);
        if (gameData != null && gameData.getLocked().equals(Boolean.FALSE)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -1);
            Date date = calendar.getTime();
            if (date.compareTo(gameData.getCreationDate()) < 0) {
                Calendar creationDate = Calendar.getInstance();
                long difference = creationDate.getTime().getTime() - gameData.getCreationDate().getTime();
                Date diff = new Date(difference);
                int positiveDiff = (diff.getSeconds() - 60) * -1;
                return diff.getSeconds() == 0 ? Integer.valueOf(0) : positiveDiff;
            }
        }
        return Integer.valueOf(0);
    }

    @Override
    public Integer gameRoundSinc(Long gameId) {
        GameData gameData = gameService.getGameById(gameId);
        if (gameData != null && gameData.getLocked().equals(Boolean.TRUE)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -1);
            Date date = calendar.getTime();
            if (date.compareTo(gameData.getRoundStartDate()) < 0) {
                Calendar creationDate = Calendar.getInstance();
                long difference = creationDate.getTime().getTime() - gameData.getRoundStartDate().getTime();
                Date diff = new Date(difference);
                int positiveDiff = (diff.getSeconds() - 60) * -1;
                return diff.getSeconds() == 0 ? Integer.valueOf(0) : positiveDiff;
            }
        }
        return Integer.valueOf(0);
    }

}
