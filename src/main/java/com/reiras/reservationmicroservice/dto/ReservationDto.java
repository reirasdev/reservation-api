package com.reiras.reservationmicroservice.dto;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reiras.reservationmicroservice.domain.enums.ReservationPayment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;

public class ReservationDto {

	private String id;
	private String status;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date eventDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date checkin;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date checkout;

	private int guestsQuant;
	private double totalPrice;
	private double downPayment;
	private String payment;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private String[] customerPhone;

	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationDto(String id, String status, Date eventDate, Date checkin, Date checkout, int guestsQuant,
			double totalPrice, double downPayment, String payment, String customerFirstName, String customerLastName,
			String customerEmail, String[] customerPhone) {
		super();
		this.id = id;
		this.status = status;
		this.eventDate = eventDate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.guestsQuant = guestsQuant;
		this.totalPrice = totalPrice;
		this.downPayment = downPayment;
		this.payment = payment;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		try {
			this.status = ReservationStatus.toEnum(Integer.valueOf(status)).getDesc();
		} catch (NumberFormatException e) {
			this.status = status;
		}
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public int getGuestsQuant() {
		return guestsQuant;
	}

	public void setGuestsQuant(int guestsQuant) {
		this.guestsQuant = guestsQuant;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public String getPayment() {
		return ReservationPayment.toEnum(Integer.valueOf(this.payment)).getDesc();
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String[] getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String[] customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationDto other = (ReservationDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReservationDto [\n id=");
		builder.append(id);
		builder.append("\n status=");
		builder.append(status);
		builder.append("\n eventDate=");
		builder.append(eventDate);
		builder.append("\n checkin=");
		builder.append(checkin);
		builder.append("\n checkout=");
		builder.append(checkout);
		builder.append("\n guestsQuant=");
		builder.append(guestsQuant);
		builder.append("\n totalPrice=");
		builder.append(totalPrice);
		builder.append("\n downPayment=");
		builder.append(downPayment);
		builder.append("\n payment=");
		builder.append(payment);
		builder.append("\n customerFirstName=");
		builder.append(customerFirstName);
		builder.append("\n customerLastName=");
		builder.append(customerLastName);
		builder.append("\n customerEmail=");
		builder.append(customerEmail);
		builder.append("\n customerPhone=");
		builder.append(Arrays.toString(customerPhone));
		builder.append("]");
		return builder.toString();
	}

}
