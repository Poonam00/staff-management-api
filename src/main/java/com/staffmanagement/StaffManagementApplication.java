package com.staffmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Staff Management Api", version = "1.0", description = "A spring boot project to manage staff"))
public class StaffManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
	}
}
