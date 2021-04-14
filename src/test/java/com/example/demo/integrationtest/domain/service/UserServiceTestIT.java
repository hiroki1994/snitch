package com.example.demo.integrationtest.domain.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.user.User;
import com.example.demo.domain.service.UserService;




@SpringBootTest
@Transactional
public class UserServiceTestIT {

	@Autowired
	UserService userService;

	@Test
	public void registration_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

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

		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			userService.create(user);
		});
	}

	@Test
	public void getUserInfo_success() throws Exception {

		String userName = "userName3";
		User user = userService.select(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void getUserInfo_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.select(userName);
		});
	}

	@Test
	public void getUserInfo_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

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

		int expected = 0;
		int actual = userService.update(user, userName);

		assertEquals(expected, actual);

	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		int expected = 1;
		int actual = userService.delete(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.delete(userName);
		});
	}

	@Test
	public void deleteUserInfo_fail_disabledUser() throws Exception {

		String userName = "disabledUser";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userService.delete(userName);
		});
	}

	@Test
	public void searchEqualUserName_success_found() throws Exception {

		String userName = "userName3";
		int expected = 1;

		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void searchEqualUserName_success_notFound() throws Exception {

		String userName = "uniqueUserName";

		int expected = 0;
		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}
}
