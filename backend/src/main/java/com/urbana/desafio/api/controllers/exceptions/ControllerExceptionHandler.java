package com.urbana.desafio.api.controllers.exceptions;


import com.urbana.desafio.services.exceptions.DatabaseExceptions;
import com.urbana.desafio.services.exceptions.ResourcesNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourcesNotFoundExceptions.class)
	public ResponseEntity<StandardErro> entityNotFoundException(ResourcesNotFoundExceptions e, HttpServletRequest request ){
		StandardErro err = new StandardErro();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Controller not found");
		err.setMessage("O recurso que você está buscando não existe");
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err) ;
	}
	
	@ExceptionHandler(DatabaseExceptions.class)
	public ResponseEntity<StandardErro> database (DatabaseExceptions e, HttpServletRequest request ){
		StandardErro err = new StandardErro();
		
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err) ;
	}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<StandardErro> database (SQLIntegrityConstraintViolationException e, HttpServletRequest request ){
		StandardErro err = new StandardErro();

		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err) ;
	}
}
