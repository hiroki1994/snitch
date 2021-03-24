package com.example.demo.integrationtest;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;




@SpringBootTest
@Transactional
public class UserServiceTestIT {

	@Autowired
	UserService userService;

	@Test
	public void signup_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

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

		boolean expected = false;
		boolean actual = userService.create(user);

		assertEquals(expected, actual);
	}

	@Test
	public void getUserInfo() throws Exception {

		String userName = "userName3";
		User user = userService.selectOne(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void updateUserInfo_success() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName5");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

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

		boolean expected = false;

		boolean actual = userService.updateOne(user, userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		boolean expected = true;
		boolean actual = userService.deleteOne(userName);

		assertEquals(expected, actual);

	}

	@Test
	public void deleteUser_fail_UserDoNotExist() throws Exception {

		String userName = "userName5";

		boolean expected = false;
		boolean actual = userService.deleteOne(userName);

		assertEquals(expected, actual);

	}

	@Test
	public void searchEqualUserName_found() throws Exception {

		String userName = "userName3";
		int expected = 1;

		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void searchEqualUserName_notFound() throws Exception {

		String userName = "uniqueUserName";

		int expected = 0;
		int actual = userService.exist(userName);

		assertEquals(expected, actual);
	}
}
