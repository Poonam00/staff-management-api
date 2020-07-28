package com.staffmanagement.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;
import com.staffmanagement.jsonview.UserViews;
import com.staffmanagement.service.v1.UserService;

@RestController
@RequestMapping(value = "v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@JsonView(UserViews.Detail.class)
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto) {
		userdto.setId(null);
		User user = modelMapper.map(userdto, User.class);
		UserDTO returnUserdto = modelMapper.map(userService.addUser(user), UserDTO.class);
		return new ResponseEntity<>(returnUserdto, HttpStatus.CREATED);
	}

	@JsonView(UserViews.Detail.class)
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userdto, @PathVariable("id") Long id) {
		User user = modelMapper.map(userdto, User.class);
		user.setId(id);
		UserDTO returnUserdto = modelMapper.map(userService.update(user), UserDTO.class);
		return new ResponseEntity<>(returnUserdto, HttpStatus.OK);
	}

	@JsonView(UserViews.Summary.class)
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		UserDTO userdto = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(userdto, HttpStatus.OK);
	}

	@JsonView(UserViews.Summary.class)
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		List<UserDTO> listdto = list.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listdto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers(@PathVariable("id") Long userId) {
		List<Customer> customers = userService.getCustomersByUserId(userId);
		List<CustomerDTO> customerdtos = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(customerdtos, HttpStatus.OK);
	}

}