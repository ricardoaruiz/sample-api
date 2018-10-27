package com.rar.sampleapi.rest.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
	
	private List<ValidationErrorFieldResponseData> fieldErrors = new ArrayList<>();
	 
    public ValidationErrorResponse() {
 
    }
 
    public void addFieldError(String path, String message) {
        ValidationErrorFieldResponseData error = new ValidationErrorFieldResponseData(path, message);
        fieldErrors.add(error);
    }

	public List<ValidationErrorFieldResponseData> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<ValidationErrorFieldResponseData> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}   
    
}
