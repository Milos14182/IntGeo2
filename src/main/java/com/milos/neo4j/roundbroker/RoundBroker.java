package com.milos.neo4j.roundbroker;

import java.util.Date;
import java.util.List;

import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;

public interface RoundBroker {

    public String createLetterForGameRound();

    public boolean waitForAllUsersToAnswer(List<SubmitAnswersTmp> answers, Long gameId, boolean collectAll);

    public boolean checkTime(Date roundStartTime);

    public String getLetter();

    public Boolean getInitalLetter();

    public Long countScore(SubmitAnswersTmp submitAnswersTmp, UserData userData);
    
    public Boolean gameCanStart(Long gameId);
}
