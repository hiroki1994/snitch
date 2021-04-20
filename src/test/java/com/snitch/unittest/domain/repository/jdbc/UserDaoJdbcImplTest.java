package com.snitch.unittest.domain.repository.jdbc;

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

import com.snitch.domain.model.user.User;
import com.snitch.domain.repository.jdbc.UserDaoJdbcImpl;

@SpringBootTest
@Transactional
public class UserDaoJdbcImplTest {

    @Autowired
    UserDaoJdbcImpl userDaoJdbcImpl;

    @Test
    public void createOneUser_suceess() throws Exception {

	User user = new User();
	user.setUserName("uniqueUserName");
	user.setMailAddress("mail@gmail.com");
	user.setPassword("7777");

	int expected = 1;
	int actual = userDaoJdbcImpl.createOne(user);

	assertEquals(expected, actual);
    }

    @Test
    public void createOneUser_fail_userNameUniqueError() throws Exception {

	User user = new User();
	user.setUserName("userName3");
	user.setMailAddress("mail@gmail.com");
	user.setPassword("7777");

	Assertions.assertThrows(DuplicateKeyException.class, () -> {
	    userDaoJdbcImpl.createOne(user);
	});
    }

    @Test
    public void selectOneUserInfo_success() throws Exception {

	String userName = "userName3";

	User user = userDaoJdbcImpl.selectOne(userName);

	assertThat(user, hasProperty("userName", equalTo("userName3")));
	assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
	assertThat(user,
		hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
    }

    @Test
    public void selectOneUserInfo_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userDaoJdbcImpl.selectOne(userName);
	});
    }

    @Test
    public void selectOneUserInfo_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userDaoJdbcImpl.selectOne(userName);
	});
    }

    @Test
    public void updateOneUserInfo_success() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName5");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	int expected = 1;
	int actual = userDaoJdbcImpl.updateOne(user, userName);

	assertEquals(expected, actual);
    }

    @Test
    public void updateOneUserInfo_success_usernameIsUnchanged() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName3");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	int expected = 1;
	int actual = userDaoJdbcImpl.updateOne(user, userName);

	assertEquals(expected, actual);
    }

    @Test
    public void updateOneUserInfo_fail_usernameUniqueError() throws Exception {

	String userName = "userName3";

	User user = new User();
	user.setUserName("userName4");
	user.setMailAddress("mailaddress3@gmail.co.jp");
	user.setPassword("password2");

	Assertions.assertThrows(DuplicateKeyException.class, () -> {
	    userDaoJdbcImpl.updateOne(user, userName);
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
	int actual = userDaoJdbcImpl.updateOne(user, userName);

	assertEquals(expected, actual);

    }

    @Test
    public void deleteOneUser_success() throws Exception {

	String userName = "userName3";

	int expected = 1;
	int actual = userDaoJdbcImpl.deleteOne(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void deleteOneUser_fail_userNameDoesNotExist() throws Exception {

	String userName = "userName5";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userDaoJdbcImpl.deleteOne(userName);
	});
    }

    @Test
    public void deleteUserInfo_fail_disabledUser() throws Exception {

	String userName = "disabledUser";

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    userDaoJdbcImpl.deleteOne(userName);
	});

    }

    @Test
    public void searchEqualUserName_success_found() throws Exception {

	String userName = "userName3";

	int expected = 1;
	int actual = userDaoJdbcImpl.exist(userName);

	assertEquals(expected, actual);
    }

    @Test
    public void searchEqualUserName_success_notFound() throws Exception {

	String userName = "uniqueUserName";

	int expected = 0;
	int actual = userDaoJdbcImpl.exist(userName);

	assertEquals(expected, actual);
    }
}