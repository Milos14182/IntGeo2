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
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.milos.neo4j")
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetails customUserDetails;
    @Autowired
    private IntgeoAuthenticationSuccessHandler intgeoAuthenticationSuccessHandler;
    @Autowired
    private IntgeoAuthenticationErrorHandler authenticationErrorHandler;

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
                .failureHandler(authenticationErrorHandler)
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    private DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetails);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
