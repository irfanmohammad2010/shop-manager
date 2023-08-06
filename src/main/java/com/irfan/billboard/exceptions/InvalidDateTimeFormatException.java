
package com.irfan.billboard.exceptions;

public class InvalidDateTimeFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDateTimeFormatException() {
		throw new InvalidDateTimeFormatException("Error: Invalid date time format, "
				+ "please provide a valid ISO DateTime format, eg : '2016-05-11T12:22:11.824Z'");
	}

	public InvalidDateTimeFormatException(String message) {
		super(message);
	}

}
