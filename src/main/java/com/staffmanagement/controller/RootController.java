package com.staffmanagement.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RootController {

	@Value("${message}")
	private String message;

	@GetMapping("/")
	public String checkActiveProfile() {
		log.info("----checkActiveProfile----");
		return message;
	}
}
