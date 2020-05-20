package com.staffmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	private final Logger log = LoggerFactory.getLogger(RootController.class);

	@Value("${message}")
	private String message;

	@GetMapping("/")
	public String checkActiveProfile() {
		log.info("----checkActiveProfile----");
		return message;
	}
}
