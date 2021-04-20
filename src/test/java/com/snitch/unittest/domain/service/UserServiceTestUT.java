package com.snitch.unittest.domain.service;

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

import com.snitch.domain.model.user.User;
import com.snitch.domain.repository.UserDao;
import com.snitch.domain.service.UserService;

@SpringBootTest
@Transactional
public class UserServiceTestUT {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOneUser_suceess() throws Exception {

	User user = new User();
	user.setUserName("uniqueUserName");
	user.setMailAddress("mail@gmail.com");
	user.setPassword("7777");

	when(userDao.createOne(user)).thenReturn(1);

	int expected = 1;
	int actual = userService.createOne(user);

	assertEquals(expected, actual);
    }

    @Test
    public void createOneUser_fail_usernameUniqueError() throws Exception {

	User user = new User();
	user.setUserName("userName3");
	user.setMailAddress("mail@gmail.com");
	user.setPassword("7777");

	when(userDao.createOne(user)).thenThrow(DuplicateKeyException.class);

	Assertions.assertThrows(DuplicateKeyException.class, () -> {
	    userService.createOne(user);
	});
    }

    @Test
    public void selectOneUserInfo_success() throws Exception {

	String userName = "userName3";
	User user = new User();

	when(userDao.selectOne(userName)).thenReturn(user);

	User expected = user;
	User actual = userService.selectOne(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void selectOneUserInfo_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	when(userDao.selectOne(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userService.selectOne(userName);
	});
    }

    @Test
    public void selectOneUserInfo_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	when(userDao.selectOne(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userService.selectOne(userName);
	});
    }

    @Test
    public void updateOneUserInfo_success() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName5");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	when(userDao.updateOne(user, userName)).thenReturn(1);

	int expected = 1;
	int actual = userService.updateOne(user, userName);

	assertEquals(expected, actual);
    }

    @Test
    public void updateOneUserInfo_success_usernameIsUnchanged() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName3");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	when(userDao.updateOne(user, userName)).thenReturn(1);

	int expected = 1;
	int actual = userService.updateOne(user, userName);

	assertEquals(expected, actual);
    }

    @Test
    public void updateOneUserInfo_fail_usernameUniqueError() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName4");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	when(userDao.updateOne(user, userName)).thenThrow(DuplicateKeyException.class);

	Assertions.assertThrows(DuplicateKeyException.class, () -> {
	    userService.updateOne(user, userName);
	});
    }

    @Test
    public void updateUserInfo_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	User user = new User();

	user.setUserName("disabledUser2");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	when(userDao.updateOne(user, userName)).thenReturn(0);

	int expected = 0;
	int actual = userService.updateOne(user, userName);

	assertEquals(expected, actual);

    }

    @Test
    public void deleteOneUser_success() throws Exception {

	String userName = "userName3";

	when(userDao.deleteOne(userName)).thenReturn(1);

	int expected = 1;
	int actual = userService.deleteOne(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneUser_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	when(userDao.deleteOne(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userService.deleteOne(userName);
	});
    }

    @Test
    public void deleteUserInfo_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	when(userDao.deleteOne(userName)).thenThrow(EmptyResultDataAccessException.class);

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userService.deleteOne(userName);
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
