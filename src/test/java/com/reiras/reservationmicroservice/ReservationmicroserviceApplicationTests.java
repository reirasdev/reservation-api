package com.reiras.reservationmicroservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.repository.ReservationRepository;
import com.reiras.reservationmicroservice.service.ReservationService;

@SpringBootTest
class ReservationmicroserviceApplicationTests {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReservationService reservationService;

	@Test
	void contextLoads() {
		assertNotNull(reservationRepository);
		assertNotNull(reservationService);
	}

}
