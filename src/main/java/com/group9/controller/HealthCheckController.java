package com.group9.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {

	@Autowired
	private ServletWebServerApplicationContext server;
	
    @GetMapping("/health")
    public String checkHealth(){

    	String currentAddress="localhost";
		try {
			currentAddress = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			
		}		
        return  "weatherApp is UP and Running on "+currentAddress;

    }
}
