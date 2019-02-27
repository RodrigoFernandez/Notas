package com.notas.core.exceptionHandler;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.notas.core.controller.exceptions.NotaNoExisteException;
import com.notas.core.controller.exceptions.UsuarioNoExisteException;
import com.notas.core.converter.exceptions.NoExisteConverter;

/**
 * me base en este: https://gustavopeiretti.com/spring-boot-capturar-las-excepciones-retornar-json-estandar/
 * 
 * https://devs4j.com/2017/09/11/spring-boot-rest-manejo-de-errores/
 * https://www.toptal.com/java/spring-boot-rest-api-error-handling
 * https://github.com/brunocleite/spring-boot-exception-handling
 * */
@ControllerAdvice
public class ErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception){
		
		LOGGER.error(exception.getMessage());
		
		String mensaje = String.join("\n", 
					exception.getBindingResult().getFieldErrors()
								.stream().map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
								.collect(Collectors.toList()));
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), mensaje, request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorInfo> authenticationException(HttpServletRequest request, AuthenticationException exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InstantiationException.class)
	public ResponseEntity<ErrorInfo> instantiationException(HttpServletRequest request, InstantiationException exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<ErrorInfo> illegalAccessException(HttpServletRequest request, IllegalAccessException exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoExisteConverter.class)
	public ResponseEntity<ErrorInfo> noExisteConverter(HttpServletRequest request, NoExisteConverter exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotaNoExisteException.class)
	public ResponseEntity<ErrorInfo> notaNoExisteException(HttpServletRequest request, NotaNoExisteException exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsuarioNoExisteException.class)
	public ResponseEntity<ErrorInfo> usuarioNoExisteException(HttpServletRequest request, UsuarioNoExisteException exception){

		LOGGER.error(exception.getMessage());
		
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}
}
