package com.shimazaki.springboot.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.shimazaki.springboot.validator.PostalCodeValidator;


@Documented
@Constraint(validatedBy = PostalCodeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface PostalCode {

	String message() default "{com.shimazaki.springboot.validator.PostalCodeValidator.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
