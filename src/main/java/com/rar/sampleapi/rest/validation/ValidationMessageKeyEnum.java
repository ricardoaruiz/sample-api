package com.rar.sampleapi.rest.validation;

public enum ValidationMessageKeyEnum {

	REQUIRED_FIELD(Constants.REQUIRED_FIELD),
	MUST_BE_GREATER_THAN_ZERO_FIELD(Constants.MUST_BE_GREATER_THAN_ZERO_FIELD), 
	ORDER_ALREADY_EXISTS(Constants.ORDER_ALREADY_EXISTS),
	ORDER_ITEMS_REQUIRED(Constants.ORDER_ITEMS_REQUIRED),
	ORDER_NOT_FOUND(Constants.ORDER_NOT_FOUND);
	
	private String key;

	ValidationMessageKeyEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public static class Constants {
		//Validation
		public static final String REQUIRED_FIELD = "required.field";
		public static final String MUST_BE_GREATER_THAN_ZERO_FIELD = "no.zero.field";
		
		//Business
		public static final String ORDER_ALREADY_EXISTS = "order.already.exists";
		public static final String ORDER_ITEMS_REQUIRED = "order.items.required";
		public static final String ORDER_NOT_FOUND = "order.not.found";
	}
	
}
