package com.milos.neo4j.roundbroker;

import com.milos.neo4j.data.GameData;
import java.util.Date;
import java.util.List;

import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;
import java.util.Map;

public interface RoundBroker {

    public String createLetterForGameRound(Long gameId);

    public boolean waitForAllUsersToAnswer(List<SubmitAnswersTmp> answers, Long gameId, boolean collectAll);

    public boolean checkTime(Date roundStartTime);

    public String getLetter();

    public Boolean getInitalLetter();

    public Map<String, Object> countScore(SubmitAnswersTmp submitAnswersTmp, UserData userData, GameData gameData);
    
    public Boolean gameCanStart(Long gameId);

    public Integer waitUsersToJoinGame(Long gameId);
    
    public Integer gameRoundSinc(Long gameId);
}
