package com.reiras.reservationmicroservice.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.exception.ObjectNotFoundException;
import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.service.CustomerService;
import com.reiras.reservationmicroservice.utiltest.TestUtils;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		Customer savedEntity = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedEntity);

		Customer foundEntity = customerService.findById(savedEntity.getId());
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());
	}

	@Test
	public void findById_NoExistingReservationIdGiven_ShouldThrowExcetion() throws Exception {
		assertThrows(ObjectNotFoundException.class, () -> {
			customerService.findById("-1");
		});
	}
}
