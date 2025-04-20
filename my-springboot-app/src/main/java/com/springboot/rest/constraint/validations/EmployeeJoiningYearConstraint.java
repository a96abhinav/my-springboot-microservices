package com.springboot.rest.constraint.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EmployeeJoiningYearConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeJoiningYearConstraint {
	String message() default "Joining year should be greater than 2010";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
