package com.reiras.reservationmicroservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reiras.reservationmicroservice.domain.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

}
