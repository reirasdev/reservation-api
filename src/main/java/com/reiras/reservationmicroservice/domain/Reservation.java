package com.reiras.reservationmicroservice.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.reiras.reservationmicroservice.domain.enums.Payment;
import com.reiras.reservationmicroservice.domain.enums.ReservationStatus;

@Document
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private int status;
	private Date eventDate;
	private Date checkin;
	private Date checkout;
	private int guestsQuant;
	private double totalPrice;
	private double downPayment;
	private int payment;
	private Customer customer;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String id, int status, Date eventDate, Date checkin, Date checkout, int guestsQuant,
			double totalPrice, double downPayment, int payment, Customer customer) {
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
		this.customer = customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReservationStatus getStatus() {
		return ReservationStatus.toEnum(this.status);
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Payment getPayment() {
		return Payment.toEnum(this.payment);
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
		Reservation other = (Reservation) obj;
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
		builder.append("Reservation [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", eventDate=");
		builder.append(eventDate);
		builder.append(", checkin=");
		builder.append(checkin);
		builder.append(", checkout=");
		builder.append(checkout);
		builder.append(", guestsQuant=");
		builder.append(guestsQuant);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append(", downPayment=");
		builder.append(downPayment);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", customer=");
		builder.append(customer);
		builder.append("]");
		return builder.toString();
	}

}
