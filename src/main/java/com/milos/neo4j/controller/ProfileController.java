package com.milos.neo4j.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.neo4j.data.CityData;
import com.milos.neo4j.data.Scoreboard;
import com.milos.neo4j.data.UserData;
import com.milos.neo4j.domain.nodes.UserGameScores;
import com.milos.neo4j.repository.UserGameScoresRepository;
import com.milos.neo4j.services.CityService;
import com.milos.neo4j.services.ScoreboardService;
import com.milos.neo4j.services.UserService;
import com.milos.neo4j.validators.EditUserProfileValidator;
import java.util.List;
import java.util.Set;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private EditUserProfileValidator editUserProfileValidator;
    @Autowired
    private ScoreboardService scoreboardService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(editUserProfileValidator);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(final HttpServletRequest request, Model model) {
        UserData userData = (UserData)request.getSession().getAttribute("userDetails");
        Set<UserGameScores> scoreboards = scoreboardService.getAllScoresForUser(userData.getUsername());
        model.addAttribute("scoreboards", scoreboards);
        return "profile";
    }

    @RequestMapping(value = "/profile/edit/{username}", method = RequestMethod.GET)
    public String editUserProfile(@PathVariable String username, Model model) {
        UserData userData = userService.getUser(username);
        Iterable<CityData> citys = cityService.findAllCitys();
        model.addAttribute("userData", userData);
        model.addAttribute("citys", citys);
        return "editUser";
    }

    @RequestMapping(value = "/profile/edit/{username}", method = RequestMethod.POST)
    public String editUserProfile(@ModelAttribute("userData") UserData user, final HttpServletRequest request,
            final HttpServletResponse response, final BindingResult errors, final Model model) {
        editUserProfileValidator.validate(user, errors);
        if (errors.hasErrors()) {
            Iterable<CityData> citys = cityService.findAllCitys();
            model.addAttribute("citys", citys);
            return "editUser";
        }
        user = userService.updateUser(user);
        request.getSession().setAttribute("userDetails", user);
        return "redirect:/profile";

    }
}
