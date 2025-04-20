package com.springboot.rest.config.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.logging.LogLevel;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Loggable {
	boolean params() default true;
	boolean result() default true;
	LogLevel value() default LogLevel.DEBUG;
}
