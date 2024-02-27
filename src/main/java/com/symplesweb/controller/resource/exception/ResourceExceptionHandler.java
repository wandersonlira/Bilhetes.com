package com.symplesweb.controller.resource.exception;

import java.time.Instant;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.symplesweb.controller.cepUtils.exception.ViaCepFormatException;
import com.symplesweb.controller.services.exceptions.DatabaseException;
import com.symplesweb.controller.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandarError> resourceNotFound(ResourceNotFoundException notFoundException,
			HttpServletRequest request) {
		
		String error = "Not Found! Consult the documentation";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError err = new StandarError(error, Instant.now(), status.value(), 
				notFoundException.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandarError> database(DatabaseException notFoundException,
			HttpServletRequest request) {
		
		String error = "Bad Request! Consult the documentation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandarError err = new StandarError(error, Instant.now(), status.value(), 
				notFoundException.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> handlerValidException(MethodArgumentNotValidException exception, 
			HttpServletRequest request) {
		
		String error = "Bad Request! Consult the documentation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandarError err = new StandarError(error, Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandarError> handlerNoSuchElementException(NoSuchElementException exception,
			HttpServletRequest request) {
		
		String error = "Bad Request! Consult the documentation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandarError standError = new StandarError(error, Instant.now(), status.value(), 
				exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(standError);
	} // Verificar se existe funcionalidade para essa exceção
	
	
	
	@ExceptionHandler(ViaCepFormatException.class)
	public ResponseEntity<StandarError> handlerValidationCepException (ViaCepFormatException exception,
			HttpServletRequest request) {
		
		String error = "Bad Request! Consult the documentation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandarError standarError = new StandarError(error, Instant.now(), status.value(),
				exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(standarError);
	}

}
