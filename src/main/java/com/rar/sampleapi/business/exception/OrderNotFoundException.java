package com.rar.sampleapi.business.exception;

public class OrderNotFoundException extends BusinessException {

	private static final long serialVersionUID = 5962280143180644125L;

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message, Object... args) {
		super(message, args);
	}

	public OrderNotFoundException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
	}

}
