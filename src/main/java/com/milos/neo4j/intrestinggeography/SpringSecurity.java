package com.milos.neo4j.intrestinggeography;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.milos.neo4j.security.CustomUserDetails;
import com.milos.neo4j.security.IntgeoAuthenticationErrorHandler;
import com.milos.neo4j.security.IntgeoAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("com.milos.neo4j")
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetails customUserDetails;

    @Autowired
    IntgeoAuthenticationSuccessHandler intgeoAuthenticationSuccessHandler;
    @Autowired
    IntgeoAuthenticationErrorHandler authenticationErrorHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/profile/**").access("hasRole('ROLE_USER')")
                .antMatchers("/play/**", "/game/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/greetingEndpoint/**").permitAll()
                .and().formLogin()
                .successHandler(intgeoAuthenticationSuccessHandler)
                .failureHandler(authenticationErrorHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetails);
    }
}
