package com.irfan.billboard.repo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.irfan.billboard.exceptions.InvalidDaysException;
import com.irfan.billboard.exceptions.InvalidTimeFormatException;

/* 
 * Utility class which stores the opening and closing times and days for the shop. 
 * Includes methods to set the timings and some utility functions 
 */
public class ShopTimingRepo {

	// Stores the opening time in format LocalTime eg "08:00" , expects and stores
	// input in UTC timezone
	private static LocalTime openingTime;
	// Stores the closing time in format LocalTime eg "08:00", expects and stores
	// input in UTC timezone
	private static LocalTime closingTime;
	// Stores the days of week when the shop is open
	private static List<DayOfWeek> openDays;

	// Util function which returns next open day based on a given day.
	public static DayOfWeek getNextOpenDay(DayOfWeek currentDay) {
		for (DayOfWeek day : openDays) {
			// day.getValue() gives a number from 1-7 corresponding to Monday-Sunday
			// Hence returns the next available date from arraylist
			if (day.getValue() > currentDay.getValue()) {
				return day;
			}
		}
		// If there is no next day in the week available, falls back to first day when
		// the shop is open
		return openDays.get(0);
	}

	public static void setOpeningTime(String openTime) {
		try {
			openingTime = LocalTime.parse(openTime);
		} catch (DateTimeParseException exception) {
			throw new InvalidTimeFormatException();
		}
	}

	public static void setClosingTime(String closeTime) {
		try {
			closingTime = LocalTime.parse(closeTime);
		} catch (DateTimeParseException exception) {
			throw new InvalidTimeFormatException();
		}
	}

	// Stores the open days from comma separated string to a sorted ArrayList
	// if input is "wed, mon, fri" the array list would contain
	// "{Monday,Wednesday,Friday}" as DayOfWeek Enum
	// Expects input in 3 letter formatted day
	public static void setOpenDays(String days) {
		if (days == null || days.length() == 0) {
			throw new InvalidDaysException();
		}
		openDays = new ArrayList<DayOfWeek>(); // clearing off the already stored values
		DateTimeFormatter dowFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("EEE")
				.toFormatter(); // formatter to convert the given string eg. 'wed' to dayOfWeek
		for (String dayString : days.split(",")) {
			try {
				DayOfWeek day = DayOfWeek.from(dowFormatter.parse(dayString.trim()));
				openDays.add(day);
			} catch (DateTimeParseException exception) {
				throw new InvalidDaysException("Given value for day: " + dayString + "is not a valid day");
			}
		}
		Collections.sort(openDays);
	}

	public static LocalTime getOpeningTime() {
		return openingTime;
	}

	public static LocalTime getClosingTime() {
		return closingTime;
	}

	public static List<DayOfWeek> getOpenDays() {
		return openDays;
	}

	// Just a util to print out the shop opening days and time
	public static String printTimings() {
		StringBuilder timings = new StringBuilder();
		timings.append("Shop opening days: ");
		timings.append(getOpenDays().toString());
		timings.append("\n");
		timings.append("Shop opening hours: ");
		timings.append(getOpeningTime().toString());
		timings.append(" - ");
		timings.append(getClosingTime().toString());
		return timings.toString();
	}

}
