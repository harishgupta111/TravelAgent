package com.travel.agent.exception;

public class TASystemException extends OQException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 477368487543575373L;

	public TASystemException() {
	}

	public TASystemException(String message) {
		super(message);
	}

	public TASystemException(Throwable cause) {
		super(cause);
	}

	public TASystemException(String message, Throwable cause) {
		super(message, cause);
	}



}
