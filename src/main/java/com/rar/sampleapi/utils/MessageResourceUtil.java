package com.rar.sampleapi.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageResourceUtil {

	@Autowired
	private MessageSource messageSource;
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
	
	private static final Locale CURRENT_LOCALE =  LocaleContextHolder.getLocale();
		
	public String getMessage(String key, String defaultMessage, Object... args) {
		String defaultMsg = RESOURCE_BUNDLE.getString(defaultMessage);
		return messageSource.getMessage(key, args, defaultMsg, CURRENT_LOCALE);
	}
	
}
