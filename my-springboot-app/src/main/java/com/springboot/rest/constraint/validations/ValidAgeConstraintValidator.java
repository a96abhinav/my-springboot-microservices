package com.springboot.rest.constraint.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidAgeConstraintValidator implements ConstraintValidator<ValidAgeConstraint, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value>=18?true:false;
	}

	

}
