package com.springboot.rest.constraint.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeJoiningYearConstraintValidator implements ConstraintValidator<EmployeeJoiningYearConstraint,Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		
		return value>2010?true:false;
	}

}
