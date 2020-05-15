package com.reiras.reservationmicroservice.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.ReservationPayment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;

public abstract class TestUtils {
	
	private static final Random RANDOM = new Random();
	
	public static Customer createRandomCustomer() {
		Customer customer = new Customer(
				null, 
				RandomStringUtils.randomAlphabetic(7, 12), 
				RandomStringUtils.randomAlphabetic(7, 12), 
				randomDate(-70, -21),
				RandomStringUtils.randomNumeric(11), 
				RandomStringUtils.randomNumeric(8), 
				RandomStringUtils.randomAlphanumeric(4, 10).concat("@email.com"), 
				new String[] { RandomStringUtils.randomNumeric(9), RandomStringUtils.randomNumeric(9) }, 
				new Address(null, 
						RandomStringUtils.randomAlphabetic(12, 20), 
						RandomStringUtils.randomNumeric(1, 3), 
						RandomStringUtils.randomAlphabetic(4, 10), 
						RandomStringUtils.randomAlphabetic(4, 10), 
						RandomStringUtils.randomNumeric(8),
						new City(null, 
								RandomStringUtils.randomAlphabetic(7, 20), 
								new State(null, 
										RandomStringUtils.randomAlphabetic(2))
								)
						)
				);
		
		return customer;
	}
	
	public static Reservation createRandomReservation() {
		double price = RANDOM.nextInt(2000) + 600;
		Date checkin = randomCheckin();
		Date checkout = randomCheckout(checkin);
		Calendar eventDate = Calendar.getInstance();
		eventDate.setTime(checkin);
		eventDate.add(Calendar.DAY_OF_MONTH, RANDOM.nextInt(366) * -1);
		
		Reservation reservation = new Reservation(
				null,
				checkout.before(new Date()) ? ReservationStatus.EXPIRED.getCode() : RANDOM.nextInt(10) % 2 == 0 ? ReservationStatus.CONFIRMED.getCode() : ReservationStatus.PENDING.getCode(),
				eventDate.getTime(),
				checkin,
				checkout,
				RANDOM.nextInt(10),
				price,
				price / 2,
				RANDOM.nextInt(10) % 2 == 0 ? ReservationPayment.CREDIT_CARD.getCode() : ReservationPayment.BANKING_BILLET.getCode(),
				createRandomCustomer()
				);
		
		return reservation;
	}
	
	private static Date randomDate(int rangeBegin, int rangeEnd) {
		Calendar startMillis = Calendar.getInstance();
		startMillis.add(Calendar.YEAR, rangeBegin);

		Calendar endMillis = Calendar.getInstance();
		endMillis.add(Calendar.YEAR, rangeEnd);

		long randomMillis = ThreadLocalRandom.current().nextLong(startMillis.getTimeInMillis(),
				endMillis.getTimeInMillis());

		return new Date(randomMillis);
	}
	
	private static Date randomCheckin() {
		Calendar checkin = Calendar.getInstance();
		checkin.setTime(randomDate(-1, 1));
		checkin.set(Calendar.HOUR_OF_DAY, 14);
		checkin.set(Calendar.MINUTE, 0);
		checkin.set(Calendar.SECOND, 0);
		
		return checkin.getTime();
	}
	
	private static Date randomCheckout(Date checkin) {
		Calendar checkout = Calendar.getInstance();
		checkout.setTime(checkin);
		checkout.add(Calendar.DAY_OF_MONTH, RANDOM.nextInt(21));
		checkout.set(Calendar.HOUR_OF_DAY, 11);
		checkout.set(Calendar.MINUTE, 0);
		checkout.set(Calendar.SECOND, 0);
		
		return checkout.getTime();
	}
	
}
