package com.travel.agent.exception;

public class OQException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8593764859168545410L;

	public OQException() {
		super();
	}

	public OQException(String message, Throwable cause) {
		super(message, cause);
	}

	public OQException(String message) {
		super(message);
	}

	public OQException(Throwable cause) {
		super(cause);
	}

}
