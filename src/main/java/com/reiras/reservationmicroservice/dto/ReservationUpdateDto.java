package com.reiras.reservationmicroservice.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ReservationUpdateDto {

	@NotEmpty(message = "{reservation.id.error}")
	private String id;

	@Min(value = 0, message = "{reservation.payment.error}")
	@Max(value = 3, message = "{reservation.payment.error}")
	private int status;

	public ReservationUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationUpdateDto(String id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + status;
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
		ReservationUpdateDto other = (ReservationUpdateDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReservationUpdateDto [id=");
		builder.append(id);
		builder.append("\n status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
