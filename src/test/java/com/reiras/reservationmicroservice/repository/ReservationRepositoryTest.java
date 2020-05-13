package com.reiras.reservationmicroservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.Payment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.repositories.ReservationRepository;

@DataMongoTest()
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void save_ValidReservationGiven_ShoulSaveNewReservation() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "John", "Doe", df.parse("03/11/1950 00:00"), "18815308920", "382039737",
				"john.doe@gmail.com", new String[] { "4128816302", "41984238073" },
				new Address(null, "Rua Artur Julião da Silva", "674", "Casa 2", "Bacacheri", "28930000",
						new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("03/12/2020 10:00"), df.parse("07/12/2020 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationRepository.save(reservation);
		assertNotNull(savedEntity);
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Mary", "Ellen", df.parse("12/06/1990 00:00"), "83090609207",
				"135064776", "mary.ellen@gmail.com", new String[] { "92998103470" },
				new Address(null, "Beco Cabiuna", "674", "Apto 101 Bl 2", "Chapada", "69050095",
						new City(null, "Manaus", new State(null, "AM"))));

		Reservation reservation = new Reservation(null, ReservationStatus.PENDING.getCode(), new Date(),
				df.parse("09/11/2020 10:00"), df.parse("16/11/2020 12:00"), 10, 2000.00, 1000.00,
				Payment.BANKING_BILLET.getCode(), customer);

		Reservation savedEntity = reservationRepository.save(reservation);
		assertNotNull(savedEntity);

		Reservation foundEntity = reservationRepository.findById(savedEntity.getId()).get();
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());
	}

	@Test
	public void findById_NoExistingReservationIdGiven_ShouldReturnEmpty() throws Exception {
		Object foundEntity = reservationRepository.findById("-1");
		assertEquals(Optional.empty(), foundEntity);
	}

	@Test
	public void findAll_NoInputParamsGiven_ShouldReturnAllReservations() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Maria", "Bela", df.parse("07/05/1994 00:00"), "31774633442",
				"448659281", "maria.bela@gmail.com", new String[] { "8336412270", "83984650504" },
				new Address(null, "Rua C, s/n", "738", null, "Povoado", "58207970",
						new City(null, "Guarabira", new State(null, "PB"))));

		Reservation reservation = new Reservation(null, ReservationStatus.EXPIRED.getCode(), new Date(),
				df.parse("31/12/2019 10:00"), df.parse("03/01/2020 12:00"), 5, 6000.00, 3000.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationRepository.save(reservation);
		assertNotNull(savedEntity);

		List<Reservation> foundEntityList = reservationRepository.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
}
