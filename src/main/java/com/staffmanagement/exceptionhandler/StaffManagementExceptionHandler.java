package com.staffmanagement.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class StaffManagementExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex) {
		log.error("ExceptionHandler: ", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
