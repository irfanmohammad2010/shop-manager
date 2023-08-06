package com.irfan.billboard.main;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

import com.irfan.billboard.repo.ShopTimingRepo;
import com.irfan.billboard.utils.DateTimeUtils;

/*
 * 	Application class which has the methods to check if a shop is open at a 
 * 	given time and find the next opening date based on a given date
 */
public class OpeningHours {

	// Method to check if the shop is open at a given date and time. Expects that
	// the provided date is in UTC
	public static boolean isOpenOn(String date) {
		ZonedDateTime zonedDateTime = DateTimeUtils.getUTCZonedDateTime(date);
		DayOfWeek day = zonedDateTime.getDayOfWeek();
		LocalTime time = zonedDateTime.toLocalTime();
		if (!ShopTimingRepo.getOpenDays().contains(day)) {
			return false;
		}

		return time.isAfter(ShopTimingRepo.getOpeningTime()) 
				&& time.isBefore(ShopTimingRepo.getClosingTime());
	}

	// Method to give next available date and time when the shop is open based on a
	// given date
	public static Instant nextOpeningDate(String date) {
		ZonedDateTime zonedDateTime = DateTimeUtils.getUTCZonedDateTime(date);
		DayOfWeek day = zonedDateTime.getDayOfWeek();
		DayOfWeek nextDay = ShopTimingRepo.getNextOpenDay(day);
		Instant nextDateTime = zonedDateTime.with(TemporalAdjusters.nextOrSame(nextDay))
				.with(ShopTimingRepo.getOpeningTime()).toInstant();
		return nextDateTime;

	}

}
