/**
 * 
 */
package com.group9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 14317
 *
 */
@AllArgsConstructor
@Data
public class LocationNotFoundException extends RuntimeException {

		 private HttpStatusCode statusCode;
		 private String error;
	

}
