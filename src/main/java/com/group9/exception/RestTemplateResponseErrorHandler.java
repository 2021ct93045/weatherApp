package com.group9.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateResponseErrorHandler 
extends DefaultResponseErrorHandler {

	@Override
	  public void handleError(ClientHttpResponse response) throws IOException {
	    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
	      try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
	        String httpBodyResponse = reader.lines().collect(Collectors.joining(""));

	        String errorMessage = httpBodyResponse;

	        throw new LocationNotFoundException(response.getStatusCode(),errorMessage);
	      }
	    }
	  }
}
