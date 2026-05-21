package com.igor.walletManager.exceptions.handler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.igor.walletManager.exceptions.custom.ConflictException;
import com.igor.walletManager.exceptions.custom.ResourceNotFoundException;
import com.igor.walletManager.exceptions.response.FieldMessage;
import com.igor.walletManager.exceptions.response.StandardError;
import com.igor.walletManager.exceptions.response.ValidationError;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationErrors(
	        MethodArgumentNotValidException ex,
	        HttpServletRequest request) {

	    List<FieldMessage> fieldErrors = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> new FieldMessage(
	                    error.getField(),
	                    error.getDefaultMessage()
	            ))
	            .toList();

	    ValidationError erros = new ValidationError(
	            LocalDateTime.now(),
	            HttpStatus.BAD_REQUEST.value(),
	            "Validation Error",
	            "Erro na validação dos campos",
	            request.getRequestURI(),
	            fieldErrors
	    );

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}

}
