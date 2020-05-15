package com.staff_management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	
	@Value("${message}")
	private String message;
	
	@RequestMapping("/")
	public String checkActiveProfile() {
		
		return message;
	}
}
