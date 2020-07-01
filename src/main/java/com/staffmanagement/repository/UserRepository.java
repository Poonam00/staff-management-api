package com.staffmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.staffmanagement.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
