package com.staffmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import com.staffmanagement.entity.User;
import com.staffmanagement.repository.UserRepository;

@Service
@EnableJpaAuditing
public class UserCrudService {

	@Autowired
	UserRepository userRepository;

	public void addUser(User user) {
		userRepository.save(user);
	}

	public User getUserById(Long userId) {
		User user = new User();
		Optional<User> opt = userRepository.findById(userId);
		if (opt.isPresent()) {
			user = opt.get();
		}
		return user;
	}

	public long count() {
		return userRepository.count();
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	public void update(User user) {
		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
}
