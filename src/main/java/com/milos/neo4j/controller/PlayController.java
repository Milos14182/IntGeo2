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
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.data.UserGameData;
import com.milos.neo4j.services.GameService;

@Controller
public class PlayController {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/play/{gameId}", method = RequestMethod.GET)
    public String playGame(HttpServletRequest request, @PathVariable Long gameId, Model model) {
        UserData userData = (UserData) request.getSession().getAttribute("userDetails");
        SubmitAnswersTmp subAnsTmp = new SubmitAnswersTmp();
        GameData gameData = gameService.getGameById(gameId);
        Long scorePerRound = 0l;
        if (userData != null) {
            UserGameData userGameData = gameService.getUserGameData(userData.getUsername(), gameId);
            if (userGameData!=null && userGameData.getScore()!=null) {
                scorePerRound = userGameData.getScore();
            }
        }
        model.addAttribute("answers", subAnsTmp);
        model.addAttribute("gameData", gameData);
        request.getSession().setAttribute("scorePerRound", scorePerRound);
        request.getSession().setAttribute("character", gameData.getFirstLetter());
        return "play";
    }
}
