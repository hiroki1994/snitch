package com.snitch.unittest.domain.model.user;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.user.User;

@SpringBootTest
public class UserTest {

    @Test
    public void setGetUser() throws Exception {

	User user = new User();
	user.setUserId((int) 10);
	user.setUserName("testUser");
	user.setMailAddress("test@gmail.com");
	user.setPassword("testpassword");
	user.setRole("ROLE_GENERAL");
	user.setEnabled(true);

	int actualId = user.getUserId();
	String actualName = user.getUserName();
	String actualMail = user.getMailAddress();
	String actualPassword = user.getPassword();
	String actualRole = user.getRole();
	boolean actualFlag = user.isEnabled();

	assertEquals(10, actualId);
	assertEquals("testUser", actualName);
	assertEquals("test@gmail.com", actualMail);
	assertEquals("testpassword", actualPassword);
	assertEquals("ROLE_GENERAL", actualRole);
	assertEquals(true, actualFlag);
    }

    @Test
    public void getUser_empty() throws Exception {

	User user = new User();
	int actualId = user.getUserId();
	String actualName = user.getUserName();
	String actualMail = user.getMailAddress();
	String actualPassword = user.getPassword();
	String actualRole = user.getRole();
	boolean actualFlag = user.isEnabled();

	assertEquals(0, actualId);
	assertEquals(null, actualName);
	assertEquals(null, actualMail);
	assertEquals(null, actualPassword);
	assertEquals(null, actualRole);
	assertEquals(false, actualFlag);
    }
}