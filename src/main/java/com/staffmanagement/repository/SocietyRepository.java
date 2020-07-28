package com.staffmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.staffmanagement.entity.Customer;
import com.staffmanagement.entity.Society;

public interface SocietyRepository extends CrudRepository<Society, Long> {
	public List<Customer> findCustomersById(Long id);

}
