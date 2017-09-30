package com.milos.neo4j.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milos.neo4j.data.GameData;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.services.GameService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping("/game")
    public String showJoinOrCreatePage(HttpServletRequest request, Model model) {
        UserData userData = (UserData) request.getSession().getAttribute("userDetails");
        Set<GameData> inactiveGames = gameService.getAllInactiveGames();
        if (userData != null) {
            Set<GameData> startedGamesForUser = gameService.getUserGames(userData);
            model.addAttribute("startedGames", startedGamesForUser);
        }
        model.addAttribute("inactiveGames", inactiveGames);
        return "joinOrCreate";
    }

    @RequestMapping("/game/createGame")
    public String createNewGame(HttpServletRequest request, @RequestParam Integer gamePoints) {
        UserData userData = (UserData) request.getSession().getAttribute("userDetails");
        GameData gameData = gameService.createNewGame(userData, gamePoints);
//        UserGameData userGameData = gameService.getUserGameData(userData.getUsername(), gameData.getId());
//        if (userGameData == null) {
            gameService.createNewUserGame(gameData.getId(), userData.getUsername(), userData.getGameScore());
//        }
        return "redirect:/play/" + gameData.getId();
    }

    @RequestMapping("/game/joinGame/{gameId}")
    public String joinGame(HttpServletRequest request, @PathVariable Long gameId, Model model) {
        UserData userData = (UserData) request.getSession().getAttribute("userDetails");
        GameData gameData = gameService.addPlayer(userData, gameId);
//        UserGameData userGameData = gameService.getUserGameData(userData.getUsername(), gameData.getId());
//        if (userGameData == null) {
//            gameService.createNewUserGame(gameData.getId(), userData.getUsername(), userData.getGameScore());
//        }
        return "redirect:/play/" + gameData.getId();
    }
}
