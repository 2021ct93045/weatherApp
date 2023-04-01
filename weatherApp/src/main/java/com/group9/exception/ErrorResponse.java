package com.group9.exception;

import lombok.Data;
@Data
public class ErrorResponse {

	private int status;

	  private String type;

	  private String error;

	  public ErrorResponse(LocationNotFoundException ex) {
	    this.status = ex.getStatusCode().value();
	    this.type = ex.getStatusCode().toString();
	    this.error = ex.getError();
	  }

	}