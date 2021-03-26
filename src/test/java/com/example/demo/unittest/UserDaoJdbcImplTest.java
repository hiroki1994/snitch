package com.example.demo.unittest;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.jdbc.UserDaoJdbcImpl;




@SpringBootTest
@Transactional
public class UserDaoJdbcImplTest {

	@Autowired
	UserDaoJdbcImpl userDaoJdbcImpl;

	@Test
	public void signup_suceess() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		int expected = 1;

		int actual = userDaoJdbcImpl.create(user);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void updateUserInfo_success() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName5");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		int expected = 1;

		int actual = userDaoJdbcImpl.updateOne(user, userName);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void getUserInfo_success() throws Exception {

		String userName = "userName3";

		User user = userDaoJdbcImpl.selectOne(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void deleteUser_success() throws Exception {

		String userName = "userName3";

		int expected = 1;
		int actual = userDaoJdbcImpl.deleteOne(userName);

		assertThat(expected, is(actual));

	}

	@Test
	public void searchEqualUserName_found() throws Exception {

		String userName = "userName3";

		int expected = 1;

		int actual = userDaoJdbcImpl.exist(userName);

		assertThat(expected, is(actual));
	}

	@Test
	public void searchEqualUserName_notFound() throws Exception {

		String userName = "uniqueUserName";

		int expected = 0;

		int actual = userDaoJdbcImpl.exist(userName);

		assertThat(expected, is(actual));
	}
}
