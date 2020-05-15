package com.reiras.reservationmicroservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String fiscalId;
	private String generalId;
	private String email;
	private String[] phone;
	private Address address;
	
	@DBRef(lazy = true)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public Customer() {

	}

	public Customer(String id, String firstName, String lastName, Date birthDate, String fiscalId, String generalId,
			String email, String[] phone, Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setBirthDate(birthDate);
		this.fiscalId = fiscalId;
		this.generalId = generalId;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFiscalId() {
		return fiscalId;
	}

	public void setFiscalId(String fiscalId) {
		this.fiscalId = fiscalId;
	}

	public String getGeneralId() {
		return generalId;
	}

	public void setGeneralId(String generalId) {
		this.generalId = generalId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
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
		Customer other = (Customer) obj;
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
		builder.append("Customer [\n id=");
		builder.append(id);
		builder.append("\n name=");
		builder.append(firstName);
		builder.append("\n lastName=");
		builder.append(lastName);
		builder.append("\n fiscalId=");
		builder.append(fiscalId);
		builder.append("\n generalId=");
		builder.append(generalId);
		builder.append("\n email=");
		builder.append(email);
		builder.append("\n phone=");
		builder.append(Arrays.toString(phone));
		builder.append("\n address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
