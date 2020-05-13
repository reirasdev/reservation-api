package com.reiras.reservationmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reiras.reservationmicroservice.domain.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
