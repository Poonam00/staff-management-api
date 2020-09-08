package com.staffmanagement.service.v1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.staffmanagement.dto.UserDTO;
import com.staffmanagement.entity.User;
import com.staffmanagement.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
	@InjectMocks
	UserService userService;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("addUserShouldAddANewUser")
	void addUserTest() {
		UserDTO userdto = new UserDTO();
		User user = new User();
		when(modelMapper.map(userdto, User.class)).thenReturn(user);

		when(userRepository.save(user)).thenReturn(user);

		UserDTO userdtoRes = new UserDTO();
		userdtoRes.setId(1L);
		when(modelMapper.map(user, UserDTO.class)).thenReturn(userdtoRes);

		UserDTO userdtoTestRes = userService.addUser(userdto);
		assertEquals(Long.valueOf(1L), userdtoTestRes.getId());
	}

	@Test
	@DisplayName("updateUserShouldUpdateAnExistingUser")
	void updateTests() {
		UserDTO userdto = new UserDTO();
		User user = new User();
		Long id = 1L;
		when(modelMapper.map(userdto, User.class)).thenReturn(user);

		when(userRepository.save(user)).thenReturn(user);

		UserDTO userdtoRes = new UserDTO();
		userdtoRes.setId(id);
		when(modelMapper.map(user, UserDTO.class)).thenReturn(userdtoRes);

		UserDTO userdtoTestRes = userService.update(userdto, id);
		assertEquals(id, userdtoTestRes.getId());
	}

	@Test
	@DisplayName("getUserByIdShouldReturnAUserWithSameId")
	void getUserByIdTest() {
		Long id = 1L;
		User user = new User();
		user.setId(id);
		Optional<User> opt = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(opt);

		UserDTO userdto = new UserDTO();
		userdto.setId(id);
		when(modelMapper.map(opt.get(), UserDTO.class)).thenReturn(userdto);

		UserDTO userdtoTestRes = userService.getUserById(1L);
		assertEquals(id, userdtoTestRes.getId());
	}

	@Test
	@DisplayName("getAllUsersShouldReturnAllTheExistingUsers")
	void getAllUsersTest() {
		List<User> userList = new ArrayList<User>();
		when(userRepository.findAll()).thenReturn(userList);

		List<UserDTO> userdtoTestRes = userService.getAllUsers();
		assertEquals(0, userdtoTestRes.size());
	}

	@Test
	@DisplayName("getCountShouldReturnTheTotalNoOfExistingUsers")
	void countTest() {
		when(userRepository.count()).thenReturn(10L);

		long count = userService.count();
		assertEquals(10L, count);
	}

}
