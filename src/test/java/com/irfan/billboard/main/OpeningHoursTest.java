package com.irfan.billboard.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.irfan.billboard.exceptions.InvalidDateTimeFormatException;
import com.irfan.billboard.main.OpeningHours;
import com.irfan.billboard.repo.ShopTimingRepo;

class OpeningHoursTest {

	String wednesday = "2016-05-11T12:22:11.824Z";
	String thursday = "2016-05-12T12:22:11.824Z";
	String wednesdayAfter4PM = "2016-05-11T16:22:11.824Z";
	String wednesdayBefore8AM = "2016-05-11T07:22:11.824Z";
	String fridayMorning = "2016-05-13T08:00:00.000Z";

	@BeforeEach
	void setUp() throws Exception {
		ShopTimingRepo.setOpeningTime("08:00");
		ShopTimingRepo.setClosingTime("16:00");
		ShopTimingRepo.setOpenDays("mon,fri,wed");
	}

	@Test
	void testIsOpenOn() {
		// Postive testcase
		assertEquals(OpeningHours.isOpenOn(wednesday), true);

		// negative testcase
		assertEquals(OpeningHours.isOpenOn(thursday), false);
		assertEquals(OpeningHours.isOpenOn(wednesdayAfter4PM), false);
		assertEquals(OpeningHours.isOpenOn(wednesdayBefore8AM), false);

		// exception testcase
		assertThrows(InvalidDateTimeFormatException.class, () -> {
			OpeningHours.isOpenOn("00000000");
		});

	}

	@Test
	void testNextOpeningDate() {
		// positive test cases
		assertEquals(OpeningHours.nextOpeningDate(wednesday), Instant.parse(fridayMorning));
	}

}
