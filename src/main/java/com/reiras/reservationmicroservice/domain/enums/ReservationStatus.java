package com.reiras.reservationmicroservice.domain.enums;

public enum ReservationStatus {

	PENDING(0, "Pending"), CONFIRMED(1, "Confirmed"), CANCELLED(2, "Cancelled"), EXPIRED(3, "Expired");

	private int code;
	private String desc;

	private ReservationStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ReservationStatus toEnum(Integer code) {

		if (code == null)
			return null;

		for (ReservationStatus x : ReservationStatus.values()) {
			if (code.equals(x.getCode()))
				return x;
		}

		throw new IllegalArgumentException("Invalid PaymentStatus: " + code);
	}
}
