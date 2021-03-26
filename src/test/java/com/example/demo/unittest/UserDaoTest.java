package com.example.demo.unittest;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;




@SpringBootTest
@Transactional
public class UserDaoTest {

	@Autowired
	@Qualifier("userDaoJdbcImpl")
	UserDao userDao;

	@Test
	public void signup_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		int expected = 1;

		int actual = userDao.create(user);

		assertEquals(expected, actual);
	}

	@Test
	public void signup_fail_userNameUniqueError() throws Exception {

		User user = new User();

		user.setUserName("userName3");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		Assertions.assertThrows(DuplicateKeyException.class, () -> {
			userDao.create(user);
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

		int actual = userDao.update(user, userName);

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
			userDao.update(user, userName);
		});
	}

	@Test
	public void getUserInfo_success() throws Exception {

		String userName = "userName3";
		User user = userDao.select(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void getUserInfo_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userDao.select(userName);
		});
	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		int expected = 1;

		int actual = userDao.delete(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteUser_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			userDao.delete(userName);
		});
	}

	@Test
	public void searchEqualUserName_success_found() throws Exception {

		String userName = "userName3";
		int expected = 1;

		int actual = userDao.exist(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void searchEqualUserName_success_notFound() throws Exception {

		String userName = "uniqueUserName";
		int expected = 0;

		int actual = userDao.exist(userName);

		assertEquals(expected, actual);
	}
}
