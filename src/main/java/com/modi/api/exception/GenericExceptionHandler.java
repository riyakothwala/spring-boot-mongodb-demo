package com.modi.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> resourceNotFoundException(Exception ex, WebRequest request) {
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
}
