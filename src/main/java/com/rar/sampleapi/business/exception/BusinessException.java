package com.rar.sampleapi.business.exception;

public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3122757529294591561L;

	private Object[] args;
	
	public BusinessException() {
		super();
	}
		
	public BusinessException(String message, Object... args) {
		super(message);
		this.args = args;
	}	

	public BusinessException(String message, Throwable cause, Object... args) {
		super(message, cause);
		this.args = args;
	}

	public Object[] getArgs() {
		return this.args;
	}
	
}
