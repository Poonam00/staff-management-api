package com.staffmanagement.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RefreshScope
public class RootController {

	@Value("${message}")
	private String message;

	@Operation(summary = "Get the active profile")
	@GetMapping("/")
	public String checkActiveProfile() {
		log.info("----checkActiveProfile----");
		return message;
	}
}
