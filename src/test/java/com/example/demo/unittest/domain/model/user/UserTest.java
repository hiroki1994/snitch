package com.example.demo.unittest.domain.model.user;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.model.user.User;

@SpringBootTest
public class UserTest {

	@Test
	public void setGetUser() throws Exception {

		User user = new User();

		user.setUserId((int)10);
		user.setUserName("testUser");
		user.setMailAddress("test@gmail.com");
		user.setPassword("testpassword");
		user.setRole("ROLE_GENERAL");
		user.setIsEnabled(0);

		int actualId = user.getUserId();
		String actualName = user.getUserName();
		String actualMail = user.getMailAddress();
		String actualPassword = user.getPassword();
		String actualRole = user.getRole();
		int actualFlag = user.getIsEnabled();

		assertEquals(10, actualId);
		assertEquals("testUser", actualName);
		assertEquals("test@gmail.com", actualMail);
		assertEquals("testpassword", actualPassword);
		assertEquals("ROLE_GENERAL", actualRole);
		assertEquals(0, actualFlag);

	}

	@Test
	public void user_empty() throws Exception {

		User user = new User();

		int actualId = user.getUserId();
		String actualName = user.getUserName();
		String actualMail = user.getMailAddress();
		String actualPassword = user.getPassword();
		String actualRole = user.getRole();
		int actualFlag = user.getIsEnabled();

		assertEquals(0, actualId);
		assertEquals(null, actualName);
		assertEquals(null, actualMail);
		assertEquals(null, actualPassword);
		assertEquals(null, actualRole);
		assertEquals(0, actualFlag);
	}
}