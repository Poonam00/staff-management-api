package com.staffmanagement.controller;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.staffmanagement.scheduler.CustomScheduler;

@RestController
public class UtilityController {
	private final Logger log = LoggerFactory.getLogger(UtilityController.class);

	@Autowired
	private CustomScheduler service;

	@PostMapping(path = "/headers", consumes = MediaType.ALL_VALUE)
	public Map<String, String> getHeaders(@RequestBody String req, @RequestHeader Map<String, String> headers) {

		log.info("----getHeaders----");
		log.info(req);

		return headers;
	}

	@PostMapping("/schedule")
	public ResponseEntity<String> schedule() {
		log.info("----schedule----");

		ExecutorService executor = Executors.newSingleThreadExecutor();
		// process request in other thread
		executor.submit(() -> service.processRequest(5));

		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

}
