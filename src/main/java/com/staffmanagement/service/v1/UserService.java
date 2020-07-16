package com.staffmanagement.service.v1;

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
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(user);
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

	public User update(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
}