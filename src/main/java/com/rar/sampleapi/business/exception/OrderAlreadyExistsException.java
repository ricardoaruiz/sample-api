package com.rar.sampleapi.business.exception;

public class OrderAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = -2671700544898307674L;

	public OrderAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public OrderAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OrderAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
