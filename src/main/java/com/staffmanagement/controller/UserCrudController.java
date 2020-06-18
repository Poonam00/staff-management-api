package com.staffmanagement.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.entity.User;
import com.staffmanagement.service.UserCrudService;

@RestController
public class UserCrudController {

	@Autowired
	UserCrudService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/user")
	public ResponseEntity<Void> createUser(@RequestBody UserDTO userdto) {
		User user = modelMapper.map(userdto, User.class);
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userdto,@PathVariable("id") Long id) {
		User user = modelMapper.map(userdto, User.class);
		user.setId(id);
		userService.update(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);

		UserDTO userdto = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(userdto, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		List<UserDTO> listdto = list.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listdto, HttpStatus.OK);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}