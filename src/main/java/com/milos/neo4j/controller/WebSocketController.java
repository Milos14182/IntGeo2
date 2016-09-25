package com.milos.neo4j.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
	@MessageMapping("/greeting")
	public String sendToGreeting(String code) throws Exception {
        Thread.sleep(2000);
        return code;
    }
	
	@RequestMapping("/start")
    public String start() {
        return "greeting";
        
    }
}
