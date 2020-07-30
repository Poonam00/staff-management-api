package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<Customer> findCustomersById(Long userId);
}
