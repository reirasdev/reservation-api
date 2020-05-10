package com.reiras.reservationmicroservice.domain.enums;

public enum Payment {

	CREDIT_CARD(1, "Credit_Card"), BANKING_BILLET(2, "Banking_Billet");

	private int code;
	private String desc;

	private Payment(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static Payment toEnum(Integer code) {

		if (code == null)
			return null;

		for (Payment x : Payment.values()) {
			if (code.equals(x.getCode()))
				return x;
		}

		throw new IllegalArgumentException("Invalid PaymentStatus: " + code);
	}
}
