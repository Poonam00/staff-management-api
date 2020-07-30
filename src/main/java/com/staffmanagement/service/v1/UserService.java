package com.staffmanagement.service.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.entity.User;
import com.staffmanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	public UserDTO addUser(UserDTO userdto) {
		userdto.setId(null);
		User user = modelMapper.map(userdto, User.class);
		return modelMapper.map(userRepository.save(user), UserDTO.class);
	}

	public UserDTO update(UserDTO userdto, Long id) {
		User user = modelMapper.map(userdto, User.class);
		user.setId(id);
		return modelMapper.map(userRepository.save(user), UserDTO.class);
	}

	public UserDTO getUserById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		User user = opt.isPresent() ? opt.get() : new User();
		return modelMapper.map(user, UserDTO.class);
	}

	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public List<CustomerDTO> getCustomersByUserId(Long userId) {
		return userRepository.findCustomersById(userId).stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
	}

	public long count() {
		return userRepository.count();
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

}