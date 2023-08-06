package com.irfan.billboard.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import com.irfan.billboard.exceptions.InvalidDateTimeFormatException;

public class DateTimeUtils {

	public static ZonedDateTime getUTCZonedDateTime(String dateTime) {
		Instant dateTimeInstant;
		ZonedDateTime zonedDateTime;
		try {
			dateTimeInstant = Instant.parse(dateTime);
			zonedDateTime = dateTimeInstant.atZone(ZoneId.of("UTC"));
		} catch (DateTimeParseException exception) {
			throw new InvalidDateTimeFormatException();
		}
		return zonedDateTime;

	}

}
