package com.rar.sampleapi.rest.validation;

public class BusinessErrorResponse {

	private String message;
	
	public BusinessErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
