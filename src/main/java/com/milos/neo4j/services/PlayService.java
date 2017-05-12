package com.milos.neo4j.services;

import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.data.UserData;

public interface PlayService {

	public Long countScore(SubmitAnswersTmp answers, UserData userData);
	
	public String choseLetter(Long gameId);
	
}
