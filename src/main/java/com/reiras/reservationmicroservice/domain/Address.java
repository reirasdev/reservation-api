package com.reiras.reservationmicroservice.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String location;
	private String number;
	private String addInfo;
	private String district;
	private String zipCode;
	private City city;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String id, String location, String number, String addInfo, String district, String zipCode,
			City city) {
		super();
		this.id = id;
		this.location = location;
		this.number = number;
		this.addInfo = addInfo;
		this.district = district;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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
		Address other = (Address) obj;
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
		builder.append("Address [\n id=");
		builder.append(id);
		builder.append("\n location=");
		builder.append(location);
		builder.append("\n number=");
		builder.append(number);
		builder.append("\n addInfo=");
		builder.append(addInfo);
		builder.append("\n district=");
		builder.append(district);
		builder.append("\n zipCode=");
		builder.append(zipCode);
		builder.append("\n city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
