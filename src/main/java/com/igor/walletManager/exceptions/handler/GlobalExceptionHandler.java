package com.igor.walletManager.exceptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.igor.walletManager.exceptions.custom.ConflictException;
import com.igor.walletManager.exceptions.custom.ResourceNotFoundException;
import com.igor.walletManager.exceptions.response.StandardError;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError(LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				"Resource Not Found",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<StandardError> conflictException(ConflictException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				"Conflict",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}
