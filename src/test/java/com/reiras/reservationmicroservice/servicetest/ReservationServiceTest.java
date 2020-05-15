package com.reiras.reservationmicroservice.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.exception.ObjectNotFoundException;
import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.service.ReservationService;
import com.reiras.reservationmicroservice.utiltest.TestUtils;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void insert_ValidReservationGiven_ShoulInsertNewReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(getReservationReadyToInsert());
		assertNotNull(savedEntity);
	}

	@Test
	public void update_ExistingReservationGiven_ShouldUpdateReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(getReservationReadyToInsert());
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getStatus(), ReservationStatus.PENDING.getCode());
		
		Reservation updatedEntity = reservationService.update(savedEntity.getId(), ReservationStatus.CONFIRMED.getCode());
		assertNotNull(updatedEntity);
		assertEquals(updatedEntity.getId(), savedEntity.getId());
		assertEquals(updatedEntity.getStatus(), ReservationStatus.CONFIRMED.getCode());
	}

	@Test
	public void update_NoExistingReservationGiven_ShouldThrowExcetion() throws Exception {
		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.update("-1", ReservationStatus.CANCELLED.getCode());
		});
	}

	@Test
	public void delete_ExistingReservationGiven_ShouldDeleteReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(getReservationReadyToInsert());
		assertNotNull(savedEntity);

		reservationService.delete(savedEntity.getId());
		
		savedEntity = reservationService.findById(savedEntity.getId());
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getStatus(), ReservationStatus.CANCELLED.getCode());
	}
	
	@Test
	public void delete_NoExistingReservationGiven_ShouldThrowExcetion() throws Exception {
		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.delete("-1");
		});
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		Reservation savedEntity = reservationService.insert(getReservationReadyToInsert());
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
		Reservation savedEntity = reservationService.insert(getReservationReadyToInsert());
		assertNotNull(savedEntity);

		List<Reservation> foundEntityList = reservationService.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
	
	private Reservation getReservationReadyToInsert() {
		Reservation reservation = TestUtils.createRandomReservation();
		Customer customer = customerRepository.save(reservation.getCustomer());
		reservation.setCustomer(customer);
		return reservation;
	}
}
