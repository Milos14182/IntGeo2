package com.milos.neo4j.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milos.neo4j.data.UserData;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(final HttpServletRequest request, final Model model) {
        final UserData user = new UserData();
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signOut(final HttpServletRequest request,
            final HttpServletResponse response, @RequestParam(required = false) String logout,
            @RequestParam(required = false) Boolean error) {
        String redirectUrl = "redirect:/";
        if (error != null && error.equals(Boolean.TRUE)) {
            return redirectUrl + "?error=true";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }
        return redirectUrl;
    }
}
