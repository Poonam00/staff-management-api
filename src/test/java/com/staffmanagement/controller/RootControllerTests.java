package com.staffmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class RootControllerTests {

	@InjectMocks
	RootController rootcontroller;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(rootcontroller, "message", "Test is running");
	}

	@Test
	@DisplayName("test active profile REST API ")
	void testActiveProfile() {
		String message = rootcontroller.checkActiveProfile();
		assertEquals("Test is running", message);
	}
}