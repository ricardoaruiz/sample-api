package com.rar.sampleapi.rest.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rar.sampleapi.business.exception.BusinessException;
import com.rar.sampleapi.utils.MessageResourceUtil;

@ControllerAdvice
public class GlobalControllerErrorHandler {

	@Autowired
	private MessageResourceUtil messageResourceUtil;
	 	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors(); 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(processFieldErrors(fieldErrors));
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessErrorResponse> processBusinessException(BusinessException ex) {
    	return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
    			new BusinessErrorResponse(resolveLocalizedErrorMessage(ex)));
    }
    
    private ValidationErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorResponse dto = new ValidationErrorResponse();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
 
        return dto;
    }
 
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
	    return messageResourceUtil.getMessage(
        		fieldError.getDefaultMessage(),
        		ValidationMessageKeyEnum.MESSAGE_NOT_FOUND.getKey(),
        		getField(fieldError.getField()));
    }
    
    private String resolveLocalizedErrorMessage(BusinessException businessError) {
    	return messageResourceUtil.getMessage(
    			businessError.getMessage(),
    			ValidationMessageKeyEnum.MESSAGE_NOT_FOUND.getKey(),
    			businessError.getArgs());
    }
    
    private String getField(String field) {
    	String[] splited = field.split("\\.");
    	
    	if(splited != null && splited.length > 0) {
    		return splited[splited.length-1];
    	}
    	
    	return field;
    }
	
}
