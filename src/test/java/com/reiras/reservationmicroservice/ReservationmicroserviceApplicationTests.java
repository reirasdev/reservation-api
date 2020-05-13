package com.reiras.reservationmicroservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.repository.ReservationRepository;
import com.reiras.reservationmicroservice.service.CustomerService;
import com.reiras.reservationmicroservice.service.ReservationService;

@SpringBootTest
class ReservationmicroserviceApplicationTests {

	@Autowired
	private ReservationService reservationService;	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
		assertNotNull(reservationService);
		assertNotNull(customerService);
		assertNotNull(reservationRepository);
		assertNotNull(customerRepository);
	}

}
