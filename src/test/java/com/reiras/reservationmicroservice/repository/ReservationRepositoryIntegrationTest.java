package com.reiras.reservationmicroservice.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.Payment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.repositories.ReservationRepository;

@RunWith(SpringRunner.class)
@DataMongoTest()
public class ReservationRepositoryIntegrationTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
		Customer customer = new Customer(null, "John", "Doe", "18815308920", "382039737", "john.doe@gmail.com",
				new String[] { "4128816302", "41984238073" }, new Address(null, "Rua Artur Julião da Silva", "674",
						"Casa 2", "Bacacheri", "28930000", new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				new Date("2020/12/03 10:00"), new Date("2020/12/07 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationRepository.save(reservation);
		assertNotNull(savedEntity);

		Reservation foundEntity = reservationRepository.findById(savedEntity.getId()).get();
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());

		List<Reservation> foundEntityList = reservationRepository.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
}
