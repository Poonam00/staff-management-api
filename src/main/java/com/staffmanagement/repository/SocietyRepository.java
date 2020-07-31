package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.Society;

public interface SocietyRepository extends JpaRepository<Society, Long> {
	public List<Customer> findCustomersById(Long id);

}
