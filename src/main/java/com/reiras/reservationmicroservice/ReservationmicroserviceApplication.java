package com.reiras.reservationmicroservice;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.Payment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.repositories.ReservationRepository;

@SpringBootApplication
public class ReservationmicroserviceApplication implements CommandLineRunner {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationmicroserviceApplication.class, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		// Add test data
		
		Customer customer1 = new Customer(null, 
				"Raphael", 
				"Eiras", 
				"22984511093", 
				"337787918", 
				"reirasdev@gmail.com", 
				new String[] {"21998763421"}, 
				new Address(null, 
						"Rua Praia Grande", 
						"33", 
						"Apto 303", 
						"Praia Grande", 
						"28930000",
						new City(null, 
								"Arraial do Cabo", 
								new State(null, 
										"RJ")
								)
						)
				);
		
		Customer customer2 = new Customer(null, 
				"Solange", 
				"Juliana", 
				"11997156709", 
				"330667257", 
				"solju@gmail.com", 
				new String[] {"22996462398"}, 
				new Address(null, 
						"Rua Praia do Forte", 
						"77", 
						"Casa 7", 
						"Centro", 
						"28930000",
						new City(null, 
								"Cabo Frio", 
								new State(null, 
										"RJ")
								)
						)
				);
	
		Reservation reservation1 = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				new Date("2020/08/10 10:00"), new Date("2020/08/15 12:00"), 5, 1000.00, 500.00,
				Payment.CREDIT_CARD.getCode(), customer1);
		
		Reservation reservation2 = new Reservation(null, ReservationStatus.EXPIRED.getCode(), new Date(),
				new Date("2020/01/05 10:00"), new Date("2020/01/12 12:00"), 5, 600.00, 300.00,
				Payment.CREDIT_CARD.getCode(), customer2);
		
		Reservation reservation3 = new Reservation(null, ReservationStatus.PENDING.getCode(), new Date(),
				new Date("2020/09/07 10:00"), new Date("2020/09/11 12:00"), 5, 1200.00, 600.00,
				Payment.BANKING_BILLET.getCode(), customer2);
		
		reservationRepository.saveAll(Arrays.asList(reservation1, reservation2, reservation3));
	}
	
	

}
