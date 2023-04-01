/**
 * 
 */
package com.group9.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 14317
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
     @ExceptionHandler(value = LocationNotFoundException.class)
    ResponseEntity<ErrorResponse> handleMyRestTemplateException(LocationNotFoundException e) {
      log.error("An error occurred: {}", e.toString());
      return new ResponseEntity<>(new ErrorResponse(e),e.getStatusCode());
    }
}
