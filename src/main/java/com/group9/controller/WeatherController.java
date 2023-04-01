package com.group9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.group9.model.Weather;
import com.group9.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api")
public class WeatherController {
		
	@Autowired
	private WeatherService weatherService;
	
	
	@Operation(summary = "Get Current Weather by location")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Found the Current Weather of Country/City", 
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Weather.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Country or City supplied", content = @Content), 
            @ApiResponse(responseCode = "404", description = "Country or City not found", content = @Content) }) 
    
	@GetMapping("/now/{country}/{city}")
    public Weather getCurrentWeather(@Parameter(description = "Country name to be searched") @PathVariable("country") String country, @Parameter(description = "City name to be searched") @PathVariable("city") String city) {
               return weatherService.getCurrentWeather(country,city);
    }
	
	
	@Operation(summary = "Get Weekly Weather by location")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Found the Weekly Weather of Country/City", 
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonNode.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid Country or City supplied", content = @Content), 
            @ApiResponse(responseCode = "404", description = "Country or City not found", content = @Content) })
    
	@GetMapping("/weekly/{country}/{city}")
    public JsonNode getWeeklyWeather(@Parameter(description = "Country name to be searched") @PathVariable("country") String country, @Parameter(description = "City name to be searched") @PathVariable("city") String city) {
               return weatherService.getForcastWeather(country,city);
    }
	
}
