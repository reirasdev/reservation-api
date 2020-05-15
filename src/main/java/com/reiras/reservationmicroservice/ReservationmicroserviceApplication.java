package com.reiras.reservationmicroservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reiras.reservationmicroservice.domain.Address;
import com.reiras.reservationmicroservice.domain.City;
import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.State;
import com.reiras.reservationmicroservice.domain.enums.ReservationPayment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.repository.ReservationRepository;

@SpringBootApplication
public class ReservationmicroserviceApplication implements CommandLineRunner {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ReservationmicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add test data
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Customer customer1 = new Customer(null, 
				"Raphael", 
				"Eiras", 
				df.parse("29/06/1983 00:00"),
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
				"Cabista", 
				df.parse("19/05/1956 00:00"),
				"11997156709", 
				"330667257", 
				"solange@gmail.com", 
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
		
		Customer customer3 = new Customer(null, 
				"Juliana", 
				"Chapecoense", 
				df.parse("24/06/1990 00:00"),
				"22888156709", 
				"110555257", 
				"juliana@gmail.com", 
				new String[] {"56996462377"}, 
				new Address(null, 
						"Estrada de Chapecó", 
						"435", 
						"Apto 10", 
						"Cidade Universitaria", 
						"25763498",
						new City(null, 
								"Chapecó", 
								new State(null, 
										"SC")
								)
						)
				);
		
		Customer customer4 = new Customer(null, 
				"Fabiana", 
				"Carioca", 
				df.parse("09/05/1987 00:00"),
				"49588156806", 
				"237555348", 
				"fabiana@gmail.com", 
				new String[] {"21987462355"}, 
				new Address(null, 
						"Av Atlantica", 
						"770", 
						"Apto 505", 
						"Copacaban", 
						"20765040",
						new City(null, 
								"Rio de Janeiro", 
								new State(null, 
										"SC")
								)
						)
				);
		
		customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4));
	
		Reservation reservation1 = new Reservation(null, ReservationStatus.CONFIRMED.getCode(), new Date(),
				df.parse("10/08/2020 10:00"), df.parse("15/08/2020 12:00"), 5, 1000.00, 500.00,
				ReservationPayment.CREDIT_CARD.getCode(), customer1);
		
		Reservation reservation2 = new Reservation(null, ReservationStatus.EXPIRED.getCode(), new Date(),
				df.parse("05/01/2020 10:00"), df.parse("12/01/2020 12:00"), 5, 600.00, 300.00,
				ReservationPayment.CREDIT_CARD.getCode(), customer2);
		
		Reservation reservation3 = new Reservation(null, ReservationStatus.PENDING.getCode(), new Date(),
				df.parse("07/09/2020 10:00"), df.parse("11/09/2020 12:00"), 5, 1200.00, 600.00,
				ReservationPayment.BANKING_BILLET.getCode(), customer2);
		
		
		reservationRepository.saveAll(Arrays.asList(reservation1, reservation2, reservation3));
		
		customer1.getReservations().add(reservation1);
		customer2.getReservations().addAll(Arrays.asList(reservation2, reservation3));
		customerRepository.saveAll(Arrays.asList(customer1, customer2));
			
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
