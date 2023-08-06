package com.irfan.billboard.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.irfan.billboard.exceptions.InvalidTimeFormatException;

class ShopTimingRepoTest {

	@Test
	void testGetNextOpenDay() {
		ShopTimingRepo.setOpeningTime("08:00");
		ShopTimingRepo.setClosingTime("16:00");
		ShopTimingRepo.setOpenDays("mon,fri,tue");

		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.MONDAY), DayOfWeek.TUESDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.TUESDAY), DayOfWeek.FRIDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.WEDNESDAY), DayOfWeek.FRIDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.THURSDAY), DayOfWeek.FRIDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.FRIDAY), DayOfWeek.MONDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.SATURDAY), DayOfWeek.MONDAY);
		assertEquals(ShopTimingRepo.getNextOpenDay(DayOfWeek.SUNDAY), DayOfWeek.MONDAY);
	}

	@Test
	void testSetOpeningTime() {
		// positive Testcase:
		ShopTimingRepo.setOpeningTime("08:00");
		LocalTime localTime = LocalTime.parse("08:00");
		assertEquals(ShopTimingRepo.getOpeningTime(), localTime);

		// negative test case
		assertThrows(InvalidTimeFormatException.class, () -> {
			ShopTimingRepo.setOpeningTime("08000");
		});

	}

	@Test
	void testSetClosingTime() {
		// positive Testcase:
		ShopTimingRepo.setClosingTime("08:00");
		LocalTime localTime = LocalTime.parse("08:00");
		assertEquals(ShopTimingRepo.getOpeningTime(), localTime);

		// negative test case
		assertThrows(InvalidTimeFormatException.class, () -> {
			ShopTimingRepo.setClosingTime("08000");
		});
	}

	@Test
	void testSetOpenDays() {
		// Positive test case
		ShopTimingRepo.setOpenDays("Mon,Wed,Fri");
		List<DayOfWeek> listOfDays = new ArrayList<DayOfWeek>();
		listOfDays.add(DayOfWeek.MONDAY);
		listOfDays.add(DayOfWeek.WEDNESDAY);
		listOfDays.add(DayOfWeek.FRIDAY);

		assertEquals(ShopTimingRepo.getOpenDays(), listOfDays);
	}

	@Test
	void testPrintTimings() {
		ShopTimingRepo.setOpeningTime("08:00");
		ShopTimingRepo.setClosingTime("16:00");
		ShopTimingRepo.setOpenDays("mon,fri,tue");

		String printTimings = "Shop opening days: [MONDAY, TUESDAY, FRIDAY]\nShop opening hours: 08:00 - 16:00";
		assertEquals(ShopTimingRepo.printTimings(), printTimings);
	}

}
