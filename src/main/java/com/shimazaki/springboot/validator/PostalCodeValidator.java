package com.shimazaki.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.shimazaki.springboot.config.PostalCode;
import com.shimazaki.springboot.entity.Area;
import com.shimazaki.springboot.repository.AreaRepository;


@ComponentScan("com.shimazaki.springboot.repository")
public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

	@Autowired
	AreaRepository repository;

	@Override
	public void initialize(PostalCode postalCode) {

	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {

		if (input == null || input.length() == 0) {
			return true;
		}

		Area result = repository.findByPostalCode(input);

		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

}
