package com.staffmanagement.service.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.staffmanagement.dto.CustomerDTO;
import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.entity.User;
import com.staffmanagement.exceptionhandler.DataNotFoundException;
import com.staffmanagement.repository.UserRepository;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@CachePut(key = "#result.id")
	public UserDTO addUser(UserDTO userdto) {
		System.out.println("cacheput users");
		userdto.setId(null);
		User user = modelMapper.map(userdto, User.class);
		return modelMapper.map(userRepository.save(user), UserDTO.class);
	}

	@CachePut(key = "#id")
	public UserDTO update(UserDTO userdto, Long id) {
		System.out.println("cacheput users");

		User user = modelMapper.map(userdto, User.class);
		user.setId(id);
		return modelMapper.map(userRepository.save(user), UserDTO.class);
	}

	@Cacheable(key = "#id", sync = true)
	public UserDTO getUserById(Long id) {
		System.out.println("cacheable users");

		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			User user = opt.get();
			return modelMapper.map(user, UserDTO.class);
		} else {
			throw new DataNotFoundException("user not found with id-" + id);
		}
	}

	@Cacheable
	public List<UserDTO> getAllUsers() {
		System.out.println("cacheable allusers");
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

	@CacheEvict(key = "#userId")
	public void deleteById(Long userId) {
		System.out.println("cacheevict users");

		userRepository.deleteById(userId);
	}

}