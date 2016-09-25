package com.milos.neo4j.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.milos.neo4j.data.UserData;
import com.milos.neo4j.services.UserService;

@Service
public class IntgeoAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	UserService userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = authentication.getName();
		UserData user = userService.getUser(username);
		request.getSession().setAttribute("userDetails", user);
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
