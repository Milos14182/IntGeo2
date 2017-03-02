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

@Service
public class RoundBrokerImpl implements RoundBroker {
	@Autowired
	PlayService playService;
	
	@Autowired
	GameService gameService;
	
	String letter = "";
	Boolean initalLetter = true;
	@Override
	public String createLetterForGameRound() {
		initalLetter = false;
		letter = playService.choseLetter();
		return letter;
	}

	@Override
	public boolean waitForAllUsersToAnswer(List<SubmitAnswersTmp> answers, Long gameId, boolean collectAll) {
		GameData gameData = gameService.getGameById(gameId);
		for (SubmitAnswersTmp submitAnswersTmp : answers) {
			if ((!submitAnswersTmp.getSubmitet() || answers.size()!=gameData.getNumberOfPlayers().intValue())
                                 && !collectAll) {
				return false;
			}
		}
		for (SubmitAnswersTmp submitAnswersTmp : answers) {
			submitAnswersTmp.setSubmitet(false);
		}
		letter = playService.choseLetter();                
                gameService.updateGameLetter(letter, gameId);
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
		return playService.countScore(submitAnswersTmp, userData);	
	}

	
	
}
