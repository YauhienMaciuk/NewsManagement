package com.epam.lab.news.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.epam.lab.news.exception.NoSuchEntityException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	private MessageSource messageSource;

	private static final Logger LOGGER = Logger.getLogger(ExceptionControllerAdvice.class);

	/**
	 * Handle validation.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleValidation(MethodArgumentNotValidException ex) {
		LOGGER.debug(ex.getMessage(), ex.getCause());
		BindingResult bindingResult = ex.getBindingResult();
		List<String> errorDetails = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorDetails.add(fieldError.getDefaultMessage());
		}
		return new ResponseEntity<List<String>>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle no such entity exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(NoSuchEntityException.class)
	public ResponseEntity<String> handleNoSuchEntityException(NoSuchEntityException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		return new ResponseEntity<String>(exception.getMessage(), receiveHttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle exception if no handler found.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> handleExceptionIfNoHandlerFound(NoHandlerFoundException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.not_found", null, LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle type mismatch exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<String> handleTypeMismatchException(TypeMismatchException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.mismatchedDate", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle illegal argument exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.illegalArgument", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Missing servlet request parameter exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handlerMissingServletRequestParameterException(
			MissingServletRequestParameterException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.illegalArgument", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handler http request method not supported exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handlerHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.illegalArgument", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handler entity not found exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handlerEntityNotFoundException(EntityNotFoundException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.illegalArgument", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}


	/**
	 * Handler jpa system exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(JpaSystemException.class)
	public ResponseEntity<String> handlerJpaSystemException(
			JpaSystemException exception) {
		LOGGER.debug(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.illegalArgument", null,
				LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle all exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception.getCause());
		String messageForClient = messageSource.getMessage("message.for_client", null, LocaleContextHolder.getLocale());
		return new ResponseEntity<String>(messageForClient, receiveHttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * The method receiveHttpHeaders() is defined for receive HttpHeaders.
	 *
	 * @return the httpHeaders
	 */
	private HttpHeaders receiveHttpHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		return responseHeaders;
	}

}