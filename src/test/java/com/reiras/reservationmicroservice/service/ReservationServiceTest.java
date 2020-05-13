package com.reiras.reservationmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.Payment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.exceptions.ObjectNotFoundException;
import com.reiras.reservationmicroservice.services.ReservationService;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService reservationService;

	@Test
	public void insert_ValidReservationGiven_ShoulInsertNewReservation() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "Insert", df.parse("03/11/1950 00:00"), "18815308920",
				"382039737", "john.doe@gmail.com", new String[] { "4128816302", "41984238073" },
				new Address(null, "Rua Artur Julião da Silva", "674", "Casa 2", "Bacacheri", "28930000",
						new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("03/12/2020 10:00"), df.parse("07/12/2020 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationService.insert(reservation);
		assertNotNull(savedEntity);
	}

	@Test
	public void update_ExistingReservationGiven_ShouldUpdateReservation() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "Update", df.parse("03/11/1950 00:00"), "18815308920",
				"382039737", "john.doe@gmail.com", new String[] { "4128816302", "41984238073" },
				new Address(null, "Rua Artur Julião da Silva", "674", "Casa 2", "Bacacheri", "28930000",
						new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("03/12/2020 10:00"), df.parse("07/12/2020 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationService.insert(reservation);
		assertNotNull(savedEntity);

		savedEntity.setStatus(ReservationStatus.CANCELLED.getCode());

		Reservation updatedEntity = reservationService.insert(savedEntity);
		assertNotNull(updatedEntity);
		assertEquals(updatedEntity.getId(), savedEntity.getId());
		assertEquals(updatedEntity.getStatus(), ReservationStatus.CANCELLED);
	}
	
	@Test
	public void delete_ExistingReservationGiven_ShouldDeleteReservation() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "Delete", df.parse("03/11/1950 00:00"), "18815308920",
				"382039737", "john.doe@gmail.com", new String[] { "4128816302", "41984238073" },
				new Address(null, "Rua Artur Julião da Silva", "674", "Casa 2", "Bacacheri", "28930000",
						new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("03/12/2020 10:00"), df.parse("07/12/2020 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationService.insert(reservation);
		assertNotNull(savedEntity);

		reservationService.delete(savedEntity.getId());
		
		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.findById(savedEntity.getId());
		});
	}

	@Test
	public void update_NoExistingReservationGiven_ShouldThrowExcetion() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "Update", df.parse("03/11/1950 00:00"), "18815308920",
				"382039737", "john.doe@gmail.com", new String[] { "4128816302", "41984238073" },
				new Address(null, "Rua Artur Julião da Silva", "674", "Casa 2", "Bacacheri", "28930000",
						new City(null, "Rio de Janeiro", new State(null, "RJ"))));

		Reservation reservation = new Reservation("-1", ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("03/12/2020 10:00"), df.parse("07/12/2020 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer);

		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.update(reservation);
		});
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "FindById", df.parse("12/06/1990 00:00"), "83090609207",
				"135064776", "mary.ellen@gmail.com", new String[] { "92998103470" },
				new Address(null, "Beco Cabiuna", "674", "Apto 101 Bl 2", "Chapada", "69050095",
						new City(null, "Manaus", new State(null, "AM"))));

		Reservation reservation = new Reservation(null, ReservationStatus.PENDING.getCode(), new Date(),
				df.parse("09/11/2020 10:00"), df.parse("16/11/2020 12:00"), 10, 2000.00, 1000.00,
				Payment.BANKING_BILLET.getCode(), customer);

		Reservation savedEntity = reservationService.insert(reservation);
		assertNotNull(savedEntity);

		Reservation foundEntity = reservationService.findById(savedEntity.getId());
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());
	}

	@Test
	public void findById_NoExistingReservationIdGiven_ShouldThrowExcetion() throws Exception {
		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.findById("-1");
		});
	}

	@Test
	public void findAll_NoInputParamsGiven_ShouldReturnAllReservations() throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Customer customer = new Customer(null, "Service", "FindAll", df.parse("07/05/1994 00:00"), "31774633442",
				"448659281", "maria.bela@gmail.com", new String[] { "8336412270", "83984650504" },
				new Address(null, "Rua C, s/n", "738", null, "Povoado", "58207970",
						new City(null, "Guarabira", new State(null, "PB"))));

		Reservation reservation = new Reservation(null, ReservationStatus.EXPIRED.getCode(), new Date(),
				df.parse("31/12/2019 10:00"), df.parse("03/01/2020 12:00"), 5, 6000.00, 3000.00,
				Payment.CREDIT_CARD.getCode(), customer);

		Reservation savedEntity = reservationService.insert(reservation);
		assertNotNull(savedEntity);

		List<Reservation> foundEntityList = reservationService.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
}
