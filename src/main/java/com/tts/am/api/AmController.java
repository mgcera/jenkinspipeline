package com.tts.am.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmController {
	

	    @RequestMapping("/")
	    public String index() {
	        return "Greetings from Spring Boot to Blue Ocean";
	    }

	}