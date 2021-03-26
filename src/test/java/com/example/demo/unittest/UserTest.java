package com.example.demo.unittest;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.User;

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
		user.setUnavailableFlag(0);

		int actualId = user.getUserId();
		String actualName = user.getUserName();
		String actualMail = user.getMailAddress();
		String actualPassword = user.getPassword();
		String actualRole = user.getRole();
		int actualFlag = user.getUnavailableFlag();

		assertEquals(10, actualId);
		assertEquals("testUser", actualName);
		assertEquals("test@gmail.com", actualMail);
		assertEquals("testpassword", actualPassword);
		assertEquals("ROLE_GENERAL", actualRole);
		assertEquals(0, actualFlag);

	}

	@Test
	public void User_empty() throws Exception {

		User user = new User();

		int actualId = user.getUserId();
		String actualName = user.getUserName();
		String actualMail = user.getMailAddress();
		String actualPassword = user.getPassword();
		String actualRole = user.getRole();
		int actualFlag = user.getUnavailableFlag();

		assertEquals(0, actualId);
		assertEquals(null, actualName);
		assertEquals(null, actualMail);
		assertEquals(null, actualPassword);
		assertEquals(null, actualRole);
		assertEquals(0, actualFlag);
	}
}
