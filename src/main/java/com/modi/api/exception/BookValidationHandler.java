package com.modi.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookValidationHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleException(MethodArgumentNotValidException e) {

		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

		List<String> errors = new ArrayList<String>();
		allErrors.forEach(error -> {
			errors.add(error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);
	}
}
