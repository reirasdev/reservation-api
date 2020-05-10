package com.reiras.reservationmicroservice.domain;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String lastName;
	private String fiscalId;
	private String generalId;
	private String email;
	private String[] phone;
	private Address address;

	public Customer() {

	}

	public Customer(String id, String name, String lastName, String fiscalId, String generalId, String email,
			String[] phone, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", fiscalId=");
		builder.append(fiscalId);
		builder.append(", generalId=");
		builder.append(generalId);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(Arrays.toString(phone));
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
