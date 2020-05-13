package com.reiras.reservationmicroservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.repositories.ReservationRepository;
import com.reiras.reservationmicroservice.util.TestUtils;

@DataMongoTest()
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void save_ValidReservationGiven_ShoulSaveNewReservation() throws Exception {
		Reservation savedEntity = reservationRepository.save(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		Reservation savedEntity = reservationRepository.save(TestUtils.createRandomReservation());
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
		Reservation savedEntity = reservationRepository.save(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);

		List<Reservation> foundEntityList = reservationRepository.findAll();
		assertNotNull(foundEntityList);
		assertTrue(foundEntityList.size() > 0);
	}
}
