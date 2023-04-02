package com.group9.controller;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {

	@Autowired
    private Environment environment;
	
    @GetMapping("/health")
    public String checkHealth(){
    	
    	String currentIp = InetAddress.getLoopbackAddress().getHostAddress();
        return  "weatherApp is UP and Running on "+currentIp;
    }
}
