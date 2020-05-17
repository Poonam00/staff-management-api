package com.staffmanagement.controller;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.staffmanagement.service.ScheduleService;

@RestController
@ControllerAdvice
public class UtilityController {

	Logger log = LoggerFactory.getLogger(UtilityController.class);

	@Autowired
	ScheduleService service;

	@GetMapping("/header")
	public Map<String, String> getHeader(@RequestHeader Map<String, String> headers) {
		return headers;
	}

	@PostMapping("/schedule")
	public ResponseEntity<?> schedule() {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		// process request in other thread
		executor.submit(() -> service.processRequest(5));

		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleAllExceptions(Exception ex) {
		log.error("@ExceptionHandler: ", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
