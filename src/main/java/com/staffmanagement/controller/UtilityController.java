package com.staff_management.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

	@GetMapping("/getHeader")
	public Map<String, String> getHeader(@RequestHeader Map<String, String> headers) {
		return headers;
	}
}
