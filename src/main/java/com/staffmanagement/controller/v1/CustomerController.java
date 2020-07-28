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

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.entity.Customer;
import com.staffmanagement.service.v1.CustomerService;

@RestController
@RequestMapping(value = "v1/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerdto) {
		customerdto.setId(null);
		Customer customer = modelMapper.map(customerdto, Customer.class);
		CustomerDTO returnCustomerdto = modelMapper.map(customerService.addCustomer(customer), CustomerDTO.class);
		return new ResponseEntity<>(returnCustomerdto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerdto,
			@PathVariable("id") Long id) {
		Customer customer = modelMapper.map(customerdto, Customer.class);
		customer.setId(id);
		CustomerDTO returnCustomerdto = modelMapper.map(customerService.update(customer), CustomerDTO.class);
		return new ResponseEntity<>(returnCustomerdto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomerById(id);
		CustomerDTO customerdto = modelMapper.map(customer, CustomerDTO.class);
		return new ResponseEntity<>(customerdto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		List<Customer> list = customerService.getAllCustomers();
		List<CustomerDTO> listdto = list.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
				.collect(Collectors.toList());
		return new ResponseEntity<>(listdto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
		customerService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
