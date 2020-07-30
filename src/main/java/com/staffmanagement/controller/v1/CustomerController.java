package com.staffmanagement.controller.v1;

import java.util.List;

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

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.service.v1.CustomerService;

@RestController
@RequestMapping(value = "v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerdto) {
		return new ResponseEntity<>(customerService.addCustomer(customerdto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerdto,
			@PathVariable("id") Long id) {
		return new ResponseEntity<>(customerService.update(customerdto, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/{id}/users")
	public ResponseEntity<List<UserDTO>> getUsers(@PathVariable("id") Long customerId) {
		return new ResponseEntity<>(customerService.getUsersByCustomerId(customerId), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getCustomerCount() {
		return new ResponseEntity<>(customerService.count(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
		customerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
