package com.milos.neo4j.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.SubmitAnswersTmp;
import com.milos.neo4j.services.GameService;

@Controller
public class PlayController {
	@Autowired
	GameService gameService;

//	@RequestMapping(value = "/play", method = RequestMethod.GET)
//	public String play(Model model, HttpServletRequest request) {
//		SubmitAnswersTmp subAnsTmp = new SubmitAnswersTmp();
//		String character = playService.choseLetter();
//		model.addAttribute("answers", subAnsTmp);
//		request.getSession().setAttribute("scorePerRound", Long.valueOf(0));
//		request.getSession().setAttribute("character", character);
//		return "play";
//	}

//	@RequestMapping(value = "/play", method = RequestMethod.POST)
//	public String play(@ModelAttribute("answers") SubmitAnswersTmp answers, HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		Long scorePerRound = (Long) request.getSession().getAttribute("scorePerRound");
//		String character = (String) request.getSession().getAttribute("character");
//		UserData userData = (UserData) request.getSession().getAttribute("userDetails");
//		scorePerRound = playService.countScore(answers, userData);
//		request.getSession().setAttribute("scorePerRound", scorePerRound);
//		character = playService.choseLetter();
//		request.getSession().setAttribute("character", character);
//		model.addAttribute("answers", new SubmitAnswersTmp());
//		return "play";
//	}

	@RequestMapping(value = "/play/{gameId}", method = RequestMethod.GET)
	public String playGame(HttpServletRequest request, @PathVariable Long gameId, Model model) {
		SubmitAnswersTmp subAnsTmp = new SubmitAnswersTmp();
		GameData gameData = gameService.getGameById(gameId);
		model.addAttribute("answers", subAnsTmp);
		model.addAttribute("gameData", gameData);
		request.getSession().setAttribute("scorePerRound", Long.valueOf(0));
		request.getSession().setAttribute("character", gameData.getFirstLetter());
		return "play";
	}
}
