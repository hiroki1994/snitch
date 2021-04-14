package com.example.demo.unittest.domain.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.user.User;
import com.example.demo.domain.repository.UserDao;
import com.example.demo.domain.service.UserService;

@SpringBootTest
@Transactional
public class UserServiceTestUT {

	@Mock
	UserDao userDao;

	@InjectMocks
	UserService userService;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registration_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		when(userDao.create(user)).thenReturn(1);

		int expected = 1;

		int actual = userService.create(user);

		assertEquals(expected, actual);
	}

	@Test
	public void registration_fail_usernameUniqueError() throws Exception {

		User user = new User();

		user.setUserName("userName3");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		when(userDao.create(user)).thenThrow(DuplicateKeyException.class);

		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			userService.create(user);
		});
	}

	@Test
	public void getUserInfo_success() throws Exception {

		String userName = "userName3";
		User user = new User();

		when(userDao.select(userName)).thenReturn(user);

		User expected = user;

		User actual = userService.select(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void getUserInfo_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(userDao.select(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.select(userName);
		});
	}

	@Test
	public void updateUserInfo_success() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName5");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.update(user, userName)).thenReturn(1);

		int expected = 1;

		int actual = userService.update(user, userName);

		assertEquals(expected, actual);
	}

	@Test
	public void updateUserInfo_success_usernameIsUnchanged() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName3");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.update(user, userName)).thenReturn(1);

		int expected = 1;
		int actual = userService.update(user, userName);

		assertEquals(expected, actual);
	}

	@Test
	public void updateUserInfo_fail_usernameUniqueError() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName4");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.update(user, userName)).thenThrow(DuplicateKeyException.class);

		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			userService.update(user, userName);
		});
	}

	@Test
	public void updateUserInfo_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

		User user = new User();

		user.setUserName("disabledUser2");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.update(user, userName)).thenReturn(0);

		int expected = 0;
		int actual = userService.update(user, userName);

		assertEquals(expected, actual);

	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		when(userDao.delete(userName)).thenReturn(1);

		int expected = 1;
		int actual = userService.delete(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(userDao.delete(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.delete(userName);
		});
	}

	@Test
	public void deleteUserInfo_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

		when(userDao.delete(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.delete(userName);
		});
	}

	@Test
	public void getUserInfo_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

		when(userDao.select(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
		    	userService.select(userName);
		});
	}

	@Test
	public void searchEqualUserName_success_found() throws Exception {

		String userName = "userName3";

		when(userDao.exist(userName)).thenReturn(1);

		int expected = 1;

		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void searchEqualUserName_success_notFound() throws Exception {

		String userName = "uniqueUserName";

		when(userDao.exist(userName)).thenReturn(0);

		int expected = 0;

		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}
}
