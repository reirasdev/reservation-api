package com.reiras.reservationmicroservice.dto;

import java.util.Date;

import javax.validation.constraints.Future;

public class ReservationInsertDto {

	@Future(message = "{reservation.future.date.error}")
	private Date checkin;

	@Future(message = "{reservation.future.date.error}")
	private Date checkout;
	
	private int guestsQuant;
	private double totalPrice;
	private double downPayment;
	private int payment;
	private String customerId;

	public ReservationInsertDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationInsertDto(Date checkin, Date checkout, int guestsQuant, double totalPrice, double downPayment,
			int payment, String customerId) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.guestsQuant = guestsQuant;
		this.totalPrice = totalPrice;
		this.downPayment = downPayment;
		this.payment = payment;
		this.customerId = customerId;
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

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkin == null) ? 0 : checkin.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
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
		ReservationInsertDto other = (ReservationInsertDto) obj;
		if (checkin == null) {
			if (other.checkin != null)
				return false;
		} else if (!checkin.equals(other.checkin))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReservationInsertDto [checkin=");
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
		builder.append("\n customerId=");
		builder.append(customerId);
		builder.append("]");
		return builder.toString();
	}

}
