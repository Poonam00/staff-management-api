package com.staffmanagement.service.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.entity.Customer;
import com.staffmanagement.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository custmerRepository;

	public CustomerDTO addCustomer(CustomerDTO customerdto) {
		customerdto.setId(null);
		Customer customer = modelMapper.map(customerdto, Customer.class);
		return modelMapper.map(custmerRepository.save(customer), CustomerDTO.class);
	}

	public CustomerDTO update(CustomerDTO customerdto, Long id) {
		Customer customer = modelMapper.map(customerdto, Customer.class);
		customer.setId(id);
		return modelMapper.map(custmerRepository.save(customer), CustomerDTO.class);
	}

	public CustomerDTO getCustomerById(Long id) {
		Optional<Customer> opt = custmerRepository.findById(id);
		Customer customer = opt.isPresent() ? opt.get() : new Customer();
		return modelMapper.map(customer, CustomerDTO.class);
	}

	public List<CustomerDTO> getAllCustomers() {
		return custmerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	public List<UserDTO> getUsersByCustomerId(Long customerId) {
		return custmerRepository.findUsersById(customerId).stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public long count() {
		return custmerRepository.count();
	}

	public void deleteById(Long customerId) {
		custmerRepository.deleteById(customerId);
	}

}
