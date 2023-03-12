package com.greatlearning.empApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlingExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<String> handleInternalServerError(RuntimeException ex) {
			String exceptionMessages=ex.getLocalizedMessage();
		return new ResponseEntity<>(exceptionMessages, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
