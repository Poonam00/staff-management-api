package com.staffmanagement.service.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;
import com.staffmanagement.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository custmerRepository;

	public Customer addCustomer(Customer customer) {
		return custmerRepository.save(customer);
	}

	public Customer getCustomerById(Long customerId) {
		Customer customer = new Customer();
		Optional<Customer> opt = custmerRepository.findById(customerId);
		if (opt.isPresent()) {
			customer = opt.get();
		}
		return customer;
	}

	public long count() {
		return custmerRepository.count();
	}

	public void deleteById(Long customerId) {
		custmerRepository.deleteById(customerId);
	}

	public Customer update(Customer customer) {
		return custmerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> list = new ArrayList<>();
		custmerRepository.findAll().forEach(list::add);
		return list;
	}
	public List<User> getUsersByCustomerId(Long customerId) {
		List<User> users = new ArrayList<>();
		custmerRepository.findUsersById(customerId).forEach(users::add);
		return users;
	}
}
