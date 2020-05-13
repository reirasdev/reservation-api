package com.reiras.reservationmicroservice.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.utiltest.TestUtils;

@DataMongoTest()
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnReservationMatchingId() throws Exception {
		Customer savedEntity = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedEntity);

		Customer foundEntity = customerRepository.findById(savedEntity.getId()).get();
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());
	}

	@Test
	public void findById_NoExistingReservationIdGiven_ShouldReturnEmpty() throws Exception {
		Object foundEntity = customerRepository.findById("-1");
		assertEquals(Optional.empty(), foundEntity);
	}
}
