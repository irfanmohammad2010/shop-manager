package com.irfan.billboard.exceptions;

public class InvalidDaysException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Please provide valid comma seperated dates in format EEE eg 'Mon' or 'mon' for Monday";

	public InvalidDaysException() {
		throw new InvalidDateTimeFormatException(MESSAGE);
	}

	public InvalidDaysException(String message) {
		super(message + " , " + MESSAGE);
	}

}
