package com.shimazaki.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shimazaki.springboot.config.NullPermissionNum;


public class NullPermissionNumValidator implements ConstraintValidator<NullPermissionNum, String> {

	@Override
	public void initialize(NullPermissionNum num) {

	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {

		if (input == null || input.length() == 0) {
			return true;
		}
		if (input.length() < 10 || input.length() > 15) {
			return false;
		} else {
			return input.matches("[0-9]*");
		}

	}

}
