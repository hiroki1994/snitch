package com.example.demo.unittest;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.UserService;




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
	public void signup_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		when(userDao.create(user)).thenReturn(1);

		boolean expected = true;
		boolean actual = userService.create(user);

		assertEquals(expected, actual);
	}

	@Test
	public void signup_fail_UsernameUniqueError() throws Exception {

		User user = new User();

		user.setUserName("userName3");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		when(userDao.create(user)).thenThrow(SQLException.class);

		boolean expected = false;
		boolean actual = userService.create(user);

		assertEquals(expected, actual);
	}

	@Test
	public void updateUserInfo_success() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName5");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.updateOne(user, userName)).thenReturn(1);

		boolean expected = true;
		boolean actual = userService.updateOne(user, userName);

		assertEquals(expected, actual);
	}

	@Test
	public void updateUserInfo_fail_UsernameUniqueError() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName4");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		when(userDao.updateOne(user, userName)).thenThrow(SQLException.class);

		boolean expected = false;
		boolean actual = userService.updateOne(user, userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		when(userDao.deleteOne(userName)).thenReturn(1);

		boolean expected = true;
		boolean actual = userService.deleteOne(userName);

		assertEquals(expected, actual);

	}

	@Test
	public void deleteUser_fail_UserDoNotExist() throws Exception {

		String userName = "userName5";

		when(userDao.deleteOne(userName)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = userService.deleteOne(userName);

		assertEquals(expected, actual);

	}

	@Test
	public void searchEqualUserName_found() throws Exception {

		String userName = "userName3";

		when(userDao.exist(userName)).thenReturn(1);

		int expected = 1;

		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void searchEqualUserName_notFound() throws Exception {

		String userName = "uniqueUserName";

		when(userDao.exist(userName)).thenReturn(0);

		int expected = 0;
		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}
}
