package com.outplaying.exceptions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.outplaying.dto.ErrorDTO;


@ControllerAdvice
public class ErrorResponseExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorDTO response = new ErrorDTO();
		response.setMessage(ex.getLocalizedMessage());
		response.setHttpErrorCode(httpStatus.value());
		
		ArrayList<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());
		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause());
		
		response.setErrors(errors);
		
		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders hh,
			HttpStatus hs, WebRequest wr) {
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		response.setHttpErrorCode(httpStatus.value());

		response.setMessage(ex.getLocalizedMessage());
		
		if (ex.getLocalizedMessage().contains("enumerable")) {
			response.setDescription("Incorrect value");
		}
		
		ArrayList<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());
		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());
		
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(RuntimeException ex, WebRequest wr) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}
	
	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(RuntimeException ex, WebRequest wr) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();

		response.setMessage(ex.getLocalizedMessage());

		ArrayList<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());

		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest wr) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex, WebRequest wr) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}
	
	
	@ExceptionHandler(ParseException.class)
    protected ResponseEntity<Object> handleParseException(ParseException ex, WebRequest wr){

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}
}
