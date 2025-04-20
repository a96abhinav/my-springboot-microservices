package com.springboot.rest.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.springboot.rest.model.GlobalExceptionResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptions(Exception ex) {

        GlobalExceptionResponseModel globalExceptionResponseModel = GlobalExceptionResponseModel
                .builder()
                .error(ex.getMessage())
                .build();
        if (ex instanceof MethodArgumentNotValidException) {
            Map<String, String> errors = new HashMap<String, String>();
            ((BindException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof ConstraintViolationException) {
            Object constraintErrorMessage = new ArrayList<>(
                    ((ConstraintViolationException) ex).getConstraintViolations()).get(0).getMessage();
            return new ResponseEntity<Object>(constraintErrorMessage, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof NoSuchElementException) {
            return new ResponseEntity<Object>(globalExceptionResponseModel, HttpStatus.NOT_FOUND);
        } else if (ex instanceof AccessDeniedException) {
            return new ResponseEntity<Object>(globalExceptionResponseModel, HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<Object>(globalExceptionResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * @ExceptionHandler(MethodArgumentNotValidException.class)
     *
     * @ResponseStatus(HttpStatus.BAD_REQUEST) public ResponseEntity<Map<String,
     * String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
     * Map<String, String> errors = new HashMap<String, String>();
     * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
     * ((FieldError) error).getField(); String errorMessage =
     * error.getDefaultMessage(); errors.put(fieldName, errorMessage); }); return
     * new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); }
     */

    /*
     * @ExceptionHandler(ConstraintViolationException.class) protected
     * ResponseEntity<Object>
     * handleConstraintViolationException(ConstraintViolationException exception) {
     *
     * Object body = new
     * ArrayList<>(exception.getConstraintViolations()).get(0).getMessage(); return
     * new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST); }
     */

}