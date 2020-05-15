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
	public void findById_ExistingCustomerIdGiven_ShouldReturnCustomerMatchingId() throws Exception {
		Customer savedEntity = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedEntity);

		Customer foundEntity = customerService.findById(savedEntity.getId());
		assertNotNull(foundEntity);
		assertEquals(savedEntity.getId(), foundEntity.getId());
	}

	@Test
	public void findById_NoExistingCustomerIdGiven_ShouldThrowExcetion() throws Exception {
		assertThrows(ObjectNotFoundException.class, () -> {
			customerService.findById("-1");
		});
	}

	@Test
	public void update_ExistingCustomerGiven_ShouldUpdateCustomer() throws Exception {
		Customer savedEntity = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedEntity);

		String newFirstName = "NEW_NAME";
		savedEntity.setFirstName(newFirstName);

		Customer updatedEntity = customerService.update(savedEntity);
		assertNotNull(updatedEntity);
		assertEquals(updatedEntity.getId(), savedEntity.getId());
		assertEquals(updatedEntity.getFirstName(), newFirstName);
	}

	@Test
	public void update_NoExistingCustomerGiven_ShouldThrowExcetion() throws Exception {
		Customer noExistingEntity = TestUtils.createRandomCustomer();
		noExistingEntity.setId("-1");

		assertThrows(ObjectNotFoundException.class, () -> {
			customerService.update(noExistingEntity);
		});
	}
}
