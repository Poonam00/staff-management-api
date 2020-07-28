package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public List<User> findUsersById(Long id);
}
