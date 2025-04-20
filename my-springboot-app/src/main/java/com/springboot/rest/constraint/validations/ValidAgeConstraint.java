package com.springboot.rest.constraint.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ValidAgeConstraintValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAgeConstraint {
	String message() default "Age should be greater than or equal to 18 years!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
