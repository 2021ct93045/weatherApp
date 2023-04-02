package com.group9.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {

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
