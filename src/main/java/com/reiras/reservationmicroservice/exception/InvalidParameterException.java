package com.reiras.reservationmicroservice.exception;

public class InvalidParameterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidParameterException(String msg) {
		super(msg);
	}
	
	public InvalidParameterException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
