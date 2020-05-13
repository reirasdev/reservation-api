package com.reiras.reservationmicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.exceptions.ObjectNotFoundException;
import com.reiras.reservationmicroservice.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation findById(String id) {
		Optional<Reservation> obj = reservationRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Reservation.class.getName()));
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public Reservation insert(Reservation obj) {
		obj.setId(null);
		return reservationRepository.save(obj);
	}

	public Reservation update(Reservation obj) {
		this.findById(obj.getId());
		return reservationRepository.save(obj);
	}

	public void delete(String id) {
		this.findById(id);
		reservationRepository.deleteById(id);
	}
}
