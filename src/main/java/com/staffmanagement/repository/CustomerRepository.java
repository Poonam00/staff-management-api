package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<User> findUsersById(Long id);
}
