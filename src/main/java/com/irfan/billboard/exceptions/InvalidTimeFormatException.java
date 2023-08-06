package com.irfan.billboard.exceptions;

public class InvalidTimeFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Error: Invalid  time format, "
			+ "please provide a valid ISO time format, eg : '08:00'";

	public InvalidTimeFormatException() {
		throw new InvalidTimeFormatException(MESSAGE);
	}

	public InvalidTimeFormatException(String message) {
		super(message);
	}
}
