package com.reiras.reservationmicroservice.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.reiras.reservationmicroservice.dto.ReservationInsertDto;
import com.reiras.reservationmicroservice.exception.FieldMessage;

public class ReservationInsertValidator implements ConstraintValidator<ReservationInsert, ReservationInsertDto> {

	@Override
	public void initialize(ReservationInsert ann) {

	}

	@Override
	public boolean isValid(ReservationInsertDto obj, ConstraintValidatorContext context) {
		List<FieldMessage> errorsList = new ArrayList<FieldMessage>();

		Calendar minimumStay = Calendar.getInstance();
		minimumStay.setTime(obj.getCheckin());
		minimumStay.add(Calendar.DAY_OF_MONTH, 1);

		if (obj.getCheckout().before(minimumStay.getTime()))
			errorsList.add(new FieldMessage("checkout", "{reservation.minimumStay.error}"));

		for (FieldMessage error : errorsList) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}

		return errorsList.isEmpty();
	}

}
