package com.rar.sampleapi.rest.validation;

public enum ValidationMessageKeyEnum {

	MESSAGE_NOT_FOUND(Constants.MESSAGE_NOT_FOUND),
	REQUIRED_FIELD(Constants.REQUIRED_FIELD),
	MUST_BE_GREATER_THAN_ZERO_FIELD(Constants.MUST_BE_GREATER_THAN_ZERO_FIELD), 
	ORDER_ALREADY_EXISTS(Constants.ORDER_ALREADY_EXISTS),
	ORDER_ITEMS_REQUIRED(Constants.ORDER_ITEMS_REQUIRED),
	ORDER_NOT_FOUND(Constants.ORDER_NOT_FOUND),
	INVALID_CREATE_REVISION_DATE(Constants.INVALID_CREATE_REVISION_DATE);
	
	private String key;

	ValidationMessageKeyEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public static class Constants {
		public static final String MESSAGE_NOT_FOUND = "message.not.found";
		
		//Validation
		public static final String REQUIRED_FIELD = "required.field";
		public static final String MUST_BE_GREATER_THAN_ZERO_FIELD = "no.zero.field";
		public static final String INVALID_CREATE_REVISION_DATE = "invalid.create.revision.date";
		
		//Business
		public static final String ORDER_ALREADY_EXISTS = "order.already.exists";
		public static final String ORDER_ITEMS_REQUIRED = "order.items.required";
		public static final String ORDER_NOT_FOUND = "order.not.found";
	}
	
}
