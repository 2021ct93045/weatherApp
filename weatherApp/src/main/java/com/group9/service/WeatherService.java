


package com.group9.service;

import java.math.BigDecimal;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group9.exception.LocationNotFoundException;
import com.group9.model.Weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {

      
    @Value("${openweather.weather.url}")
    private String OPENWEATHER_WEATHER_URL;
    
    @Value("${openweather.forecast.url}")
    private String OPENWEATHER_FORECAST_URL;
    
    @Value("${openweather.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

   
  	public Weather getCurrentWeather(String country,String city) throws LocationNotFoundException{
    	log.info("Requesting current weather for {}/{}", country, city);
        URI url = new UriTemplate(OPENWEATHER_WEATHER_URL).expand(city, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("Response Status:{}", response.getStatusCodeValue());
        return convert(response);
    }
    
   
	public JsonNode getForcastWeather(String country,String city) {
    	log.info("Requesting weather forecast for {}/{}", country, city);
        URI url = new UriTemplate(OPENWEATHER_FORECAST_URL).expand(city, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("Response Status:{}", response.getStatusCodeValue());
        if(response.getStatusCodeValue()!=HttpStatus.OK.value()) {
            throw new RuntimeException("Invalid Country or State");
        }
		try {
			JsonNode root = objectMapper.readTree(response.getBody());
	        return root.path("list");
		} catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Error parsing response:%s", response.getBody()));
        }

    }

    private Weather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new Weather(root.path("weather").get(0).path("main").asText(),
                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
                    BigDecimal.valueOf(root.path("wind").path("speed").asDouble()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
    
    
}
