package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<Customer> findCustomersById(Long userId);
}
