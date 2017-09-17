/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milos.neo4j.controller;

import com.milos.neo4j.data.Scoreboard;
import com.milos.neo4j.services.ScoreboardService;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author milos
 */
@Controller
public class ScoreboardController {

    @Autowired
    private ScoreboardService scoreboardService;

    @RequestMapping(value = "/scoreboard", method = RequestMethod.GET)
    public String getFullScoreboard(HttpServletRequest request, Model model) {
        String type = request.getParameter("type") !=null ? request.getParameter("type") : "";
        Iterable<Scoreboard> scoreboards = new LinkedList<>();
        switch (type) {
            case "montly":
                scoreboards = scoreboardService.getMontlyScoreboard();
                break;
            case "weekly":
                scoreboards = scoreboardService.getWeeklyScoreboard();
                break;
            case "daily":
                scoreboards = scoreboardService.getFullScoreboard();
                break;
            case "winnings":
                List<Scoreboard> winnings = scoreboardService.getWinningsScoreboard();
                scoreboards = new LinkedList<>(winnings);
                break;
            default:
                type = "montly";
                scoreboards = scoreboardService.getMontlyScoreboard();
                break;
        }
        model.addAttribute("scoreboards", scoreboards);
        model.addAttribute("type", type);
        return "scoreboard";
    }
}
