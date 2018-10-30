package com.rar.sampleapi.business.exception;

public class OrderAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = -2671700544898307674L;

	public OrderAlreadyExistsException() {
		super();
	}

	public OrderAlreadyExistsException(String message, Object... args) {
		super(message, args);
	}

	public OrderAlreadyExistsException(String message, Throwable cause, Object... args) {
		super(message, cause, args);
	}



}
