package com.yaksha.assignment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeOperationsAssignment {

	public static void main(String[] args) {

		// Task 1: Get Current Date and Time
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Current Date and Time: " + now);

		// Task 2: Date Arithmetic
		LocalDate date = LocalDate.now();
		System.out.println("Date after adding 5 days: " + date.plusDays(5));

		// Task 3: Time Arithmetic
		LocalTime time = LocalTime.now();
		System.out.println("Time after subtracting 3 hours: " + time.minusHours(3));

		// Task 4: Time Zones
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("Current Time in UTC: " + zonedDateTime);
	}
}
