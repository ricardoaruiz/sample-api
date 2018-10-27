package com.rar.sampleapi.rest.validation;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rar.sampleapi.business.exception.BusinessException;

@ControllerAdvice
public class GlobalControllerErrorHandler {

	@Autowired
	private MessageSource messageSource;
	 	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors(); 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(processFieldErrors(fieldErrors));
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessErrorResponse> processBusinessException(BusinessException ex) {
    	return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
    			new BusinessErrorResponse(resolveLocalizedErrorMessage(ex.getMessage())));
    }
    
    private ValidationErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorResponse dto = new ValidationErrorResponse();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(
            		fieldError.getDefaultMessage());
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
 
        return dto;
    }
 
    private String resolveLocalizedErrorMessage(String messageKey) {
    	String localizedErrorMessage; 
    	try {
	        Locale currentLocale =  LocaleContextHolder.getLocale();
	        localizedErrorMessage = messageSource.getMessage(
	        		messageKey, null, currentLocale);
    	} catch (Exception e) {
    		localizedErrorMessage = messageKey;
		} 
        return localizedErrorMessage;
    }    
	
}
