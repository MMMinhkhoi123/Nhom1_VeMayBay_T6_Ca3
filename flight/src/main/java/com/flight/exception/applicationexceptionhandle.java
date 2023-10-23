package com.flight.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flight.exception.entity_ex.location_ex;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class applicationexceptionhandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value =  { ConstraintViolationException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(
			ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((e) -> {
			 String errorMessage = e.getMessage();
			 String type = e.getPropertyPath().toString();
			errors.put(type, errorMessage);
		});
	    return errors;
	}
	
	@ExceptionHandler(value = location_ex.class)
	public ResponseEntity<Object> exception_img(location_ex exception) {
		return new ResponseEntity<>(exception.getMess(), HttpStatus.NOT_FOUND);
	}
}
