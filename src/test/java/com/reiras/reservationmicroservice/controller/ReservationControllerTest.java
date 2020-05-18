package com.reiras.reservationmicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.domain.Reservation;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;
import com.reiras.reservationmicroservice.dto.ReservationDto;
import com.reiras.reservationmicroservice.dto.ReservationInsertDto;
import com.reiras.reservationmicroservice.dto.ReservationUpdateDto;
import com.reiras.reservationmicroservice.repository.CustomerRepository;
import com.reiras.reservationmicroservice.repository.ReservationRepository;
import com.reiras.reservationmicroservice.util.TestUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private RequestSpecification requestSetup() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("http://localhost:" + port);
		requestSpecBuilder.setBasePath("/reservations");
		return requestSpecBuilder.build();
	}

	@Test
	public void insert_ValidReservationGiven_ShoulInsertNewReservation() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		String newReservationUri = response.getHeader("Location");

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri(newReservationUri);

		Response newReservationResponse = RestAssured.given(requestSpecBuilder.build()).get();
		assertEquals(HttpStatus.OK.value(), newReservationResponse.getStatusCode());
		assertEquals(newReservationResponse.as(ReservationDto.class).getTotalPrice(), newReservation.getTotalPrice());
		assertEquals(newReservationResponse.as(ReservationDto.class).getCustomerFirstName(),
				savedCustomer.getFirstName());
	}

	@Test
	public void insert_ReservationNoFutureCheckinGiven_ShoulReturnError422() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());
		newReservation.setCheckin(yesterday.getTime());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_ReservationInvalidMinimumStayGiven_ShoulReturnError422() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());
		newReservation.setCheckout(newReservation.getCheckin());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_ReservationInvalidGuestsQuantGiven_ShoulReturnError422() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());
		newReservation.setGuestsQuant(0);

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_ReservationInvalidTotalPriceGiven_ShoulReturnError422() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());
		newReservation.setTotalPrice(0);

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_ReservationInvalidPaymentGiven_ShoulReturnError422() throws Exception {
		Customer savedCustomer = customerRepository.save(TestUtils.createRandomCustomer());
		assertNotNull(savedCustomer);

		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId(savedCustomer.getId());
		newReservation.setPayment(0);

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_ReservationEmptyCustomerIdGiven_ShoulReturnError422() throws Exception {
		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId("");

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void insert_NoExistingReservationCustomerGiven_ShoulReturnError404() throws Exception {
		ReservationInsertDto newReservation = new ModelMapper().map(TestUtils.createRandomReservation(),
				ReservationInsertDto.class);
		newReservation.setCustomerId("-1");

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(newReservation).post();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	@Test
	public void update_ValidReservationGiven_ShoulUpdateReservation() throws Exception {
		Reservation savedEntity = TestUtils.createRandomReservation();
		savedEntity.setStatus(ReservationStatus.PENDING.getCode());
		savedEntity = reservationRepository.save(savedEntity);
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getStatus(), ReservationStatus.PENDING.getCode());

		ReservationUpdateDto updateEntity = new ReservationUpdateDto(savedEntity.getId(),
				ReservationStatus.CONFIRMED.getCode());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(updateEntity).put("/" + savedEntity.getId());
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		String updateReservationUri = response.getHeader("Location");

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri(updateReservationUri);

		Response updateReservationResponse = RestAssured.given(requestSpecBuilder.build()).get();
		assertEquals(HttpStatus.OK.value(), updateReservationResponse.getStatusCode());
		assertEquals(updateReservationResponse.as(ReservationDto.class).getId(), savedEntity.getId());
		assertEquals(updateReservationResponse.as(ReservationDto.class).getStatus(),
				ReservationStatus.CONFIRMED.getDesc());
	}

	@Test
	public void update_NoExistingReservationGiven_ShoulReturnError404() throws Exception {
		ReservationUpdateDto updateEntity = new ReservationUpdateDto("-1", ReservationStatus.CONFIRMED.getCode());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(updateEntity).put("/-1");
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	@Test
	public void update_ReservationNotMatchIdParamsGiven_ShoulReturnError422() throws Exception {
		ReservationUpdateDto updateEntity = new ReservationUpdateDto("ID_1", ReservationStatus.CONFIRMED.getCode());

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(updateEntity).put("/ID_2");
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void update_ReservationInvalidStatusGiven_ShoulReturnError422() throws Exception {
		ReservationUpdateDto updateEntity = new ReservationUpdateDto("ID_1", 9999);

		Response response = RestAssured.given(this.requestSetup()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(updateEntity).put("/ID_1");
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode());
	}

	@Test
	public void delete_ValidReservationGiven_ShoulDeleteReservation() throws Exception {
		Reservation savedEntity = TestUtils.createRandomReservation();
		savedEntity.setStatus(ReservationStatus.CONFIRMED.getCode());
		savedEntity = reservationRepository.save(savedEntity);
		assertNotNull(savedEntity);
		assertEquals(savedEntity.getStatus(), ReservationStatus.CONFIRMED.getCode());

		Response response = RestAssured.given(this.requestSetup()).delete("/" + savedEntity.getId());
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());

		Reservation deleteEntity = reservationRepository.findById(savedEntity.getId()).get();
		assertNotNull(deleteEntity);
		assertEquals(deleteEntity.getId(), savedEntity.getId());
		assertEquals(deleteEntity.getStatus(), ReservationStatus.CANCELLED.getCode());
	}

	@Test
	public void delete_NoExistingeservationGiven_ShoulReturnError404() throws Exception {
		Response response = RestAssured.given(this.requestSetup()).delete("/-1");
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	@Test
	public void findById_ExistingReservationIdGiven_ShouldReturnNewReservationUri() {
		Reservation savedEntity = reservationRepository.save(TestUtils.createRandomReservation());
		assertNotNull(savedEntity);

		Response response = RestAssured.given(this.requestSetup()).get("/" + savedEntity.getId());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertEquals(response.as(ReservationDto.class).getId(), savedEntity.getId());
	}

	@Test
	public void findById_NoExistingReservationIdGiven_ShoulReturnError404() {
		Response response = RestAssured.given(this.requestSetup()).get("/-1");
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}

	@Test
	public void findAll_NoInputParamsGiven_ShouldReturnAllReservations() {
		Response response = RestAssured.given(this.requestSetup()).get();
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}
}
