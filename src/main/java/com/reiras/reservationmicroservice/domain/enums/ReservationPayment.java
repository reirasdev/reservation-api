package com.reiras.reservationmicroservice.domain.enums;

public enum ReservationPayment {

	CREDIT_CARD(1, "Credit_Card"), BANKING_BILLET(2, "Banking_Billet");

	private int code;
	private String desc;

	private ReservationPayment(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static ReservationPayment toEnum(Integer code) {

		if (code == null)
			return null;

		for (ReservationPayment x : ReservationPayment.values()) {
			if (code.equals(x.getCode()))
				return x;
		}

		throw new IllegalArgumentException("Invalid PaymentStatus: " + code);
	}
}
