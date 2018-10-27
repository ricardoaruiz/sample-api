package com.rar.sampleapi.rest.validation;

public enum ValidationMessageKeyEnum {

	REQUIRED_FIELD(Constants.REQUIRED_FIELD),
	ORDER_ALREADY_EXISTS(Constants.ORDER_ALREADY_EXISTS),
	ORDER_ITEMS_REQUIRED(Constants.ORDER_ITEMS_REQUIRED),
	MUST_BE_GREATER_THAN_ZERO_FIELD(Constants.MUST_BE_GREATER_THAN_ZERO_FIELD);
	
	private String key;

	ValidationMessageKeyEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
	public static class Constants {
		public static final String REQUIRED_FIELD = "required.field";
		public static final String ORDER_ALREADY_EXISTS = "order.already.exists";
		public static final String ORDER_ITEMS_REQUIRED = "order.items.required";
		public static final String MUST_BE_GREATER_THAN_ZERO_FIELD = "no.zero.field";
	}
	
}
