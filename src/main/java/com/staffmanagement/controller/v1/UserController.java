package com.staffmanagement.controller.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.jsonview.UserViews;
import com.staffmanagement.service.v1.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@JsonView(UserViews.Detail.class)
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto) {
		return new ResponseEntity<>(userService.addUser(userdto), HttpStatus.CREATED);
	}

	@JsonView(UserViews.Detail.class)
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userdto, @PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.update(userdto, id), HttpStatus.OK);
	}

	@JsonView(UserViews.Summary.class)
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
		System.out.println(id);
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	@JsonView(UserViews.Summary.class)
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers(@PathVariable("id") Long userId) {
		return new ResponseEntity<>(userService.getCustomersByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getUserCount() {
		return new ResponseEntity<>(userService.count(), HttpStatus.OK);
	}

	@Operation(summary = "Deletes a user", description = "", tags = { "user" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "user not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}