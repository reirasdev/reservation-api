package com.reiras.reservationmicroservice.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.exception.ObjectNotFoundException;
import com.reiras.reservationmicroservice.service.ReservationService;
import com.reiras.reservationmicroservice.utiltest.TestUtils;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService reservationService;

	@Test
	public void insert_ValidReservationGiven_ShoulInsertNewReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);
	}

	@Test
	public void update_ExistingReservationGiven_ShouldUpdateReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);

		ReservationStatus updateStatus;
		if (savedEntity.getStatus() == ReservationStatus.CONFIRMED.getCode() || savedEntity.getStatus() == ReservationStatus.PENDING.getCode())
			updateStatus = ReservationStatus.CANCELLED;
		else
			updateStatus = ReservationStatus.PENDING;

		savedEntity.setStatus(updateStatus.getCode());

		Reservation updatedEntity = reservationService.insert(savedEntity);
		assertNotNull(updatedEntity);
		assertEquals(updatedEntity.getId(), savedEntity.getId());
		assertEquals(updatedEntity.getStatus(), updateStatus.getCode());
	}

	@Test
	public void update_NoExistingReservationGiven_ShouldThrowExcetion() throws Exception {
		Reservation reservation = TestUtils.createRandomReservation();
		reservation.setId("-1");

		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.update(reservation);
		});
	}

	@Test
	public void delete_ExistingReservationGiven_ShouldDeleteReservation() throws Exception {
		Reservation savedEntity = reservationService.insert(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);

		reservationService.delete(savedEntity.getId());

		assertThrows(ObjectNotFoundException.class, () -> {
			reservationService.findById(savedEntity.getId());
		});
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		Reservation savedEntity = reservationService.insert(TestUtils.createRandomReservation());
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
		Reservation savedEntity = reservationService.insert(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);

		List<Reservation> foundEntityList = reservationService.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
}
