package com.irfan.billboard.main;

import com.irfan.billboard.repo.ShopTimingRepo;

public class MainClass {

	public static void main(String[] args) {
		ShopTimingRepo.setOpeningTime("08:00:00");
		ShopTimingRepo.setClosingTime("16:00");
		ShopTimingRepo.setOpenDays("Mon, Wed, Fri");

		String wednesday = "2016-05-11T12:22:11.824Z";
		String thursday = "2016-05-12T12:22:11.824Z";

		System.out.println(ShopTimingRepo.printTimings());
		System.out.println(OpeningHours.isOpenOn(wednesday));
		System.out.println(OpeningHours.isOpenOn(thursday));
		System.out.println(OpeningHours.nextOpeningDate(wednesday));

	}

}
