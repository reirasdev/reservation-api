package com.reiras.reservationmicroservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.exception.ObjectNotFoundException;
import com.reiras.reservationmicroservice.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CustomerService customerService;

	public Reservation findById(String id) {
		if (id == null || id.isBlank())
			throw new ObjectNotFoundException(
					"Object not found! Id is invalid: " + id + ", Type: " + Reservation.class.getName());

		Optional<Reservation> obj = reservationRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Reservation.class.getName()));
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public Page<Reservation> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return reservationRepository.findAll(pageRequest);
	}

	public Reservation insert(Reservation obj) {
		Customer customer = customerService.findById(obj.getCustomer().getId());

		obj.setId(null);
		obj.setEventDate(new Date());
		obj.setStatus(ReservationStatus.PENDING.getCode());
		obj.setCustomer(customer);
		obj = reservationRepository.save(obj);

		customer.getReservations().add(obj);
		customerService.update(customer);

		return obj;
	}

	public Reservation update(String id, int status) {
		Reservation obj = this.findById(id);
		ReservationStatus.toEnum(status);
		obj.setStatus(status);
		return reservationRepository.save(obj);
	}

	public void delete(String id) {
		Reservation obj = this.findById(id);
		obj.setStatus(ReservationStatus.CANCELLED.getCode());
		reservationRepository.save(obj);
	}
}
