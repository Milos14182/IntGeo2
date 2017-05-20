package com.milos.neo4j.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.neo4j.data.UserData;
import com.milos.neo4j.data.UserGameData;
import com.milos.neo4j.services.GameService;
import com.milos.neo4j.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	GameService gameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(final HttpServletRequest request, final Model model) {
		final UserData user = new UserData();
		model.addAttribute("user", user);
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signOut(final HttpServletRequest request,
			final HttpServletResponse response, @RequestParam String logout) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}

	@RequestMapping(value = "/points", method = RequestMethod.GET)
	public String points(final HttpServletRequest request, final Model m) {
		UserData userData = (UserData) request.getSession().getAttribute("userDetails");
		Set<UserGameData> gameDatas = gameService.getAllGamesForPlayer(userData);
		m.addAttribute("gameDatas", gameDatas);
		m.addAttribute("userData", userData);
		return "points";
	}

	@RequestMapping(value = "/how_to", method = RequestMethod.GET)
	public String howto(final Model m) {
		return "howTo";
	}
}
