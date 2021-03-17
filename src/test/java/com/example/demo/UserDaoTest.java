package com.example.demo;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;




@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/Delete.sql", "/Schema.sql", "/Insert.sql"})
public class UserDaoTest {

	@Autowired
	@Qualifier("userDaoJdbcImpl")
	UserDao userDao;

	@Test
	public void 新規登録() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		int expected = 1;

		int actual = userDao.insertOne(user);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void 登録情報更新() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName4");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		int expected = 1;

		int actual = userDao.updateOne(user, userName);

		assertThat(expected, equalTo(actual));


	}

	@Test
	public void 登録情報取得() throws Exception {

		String userName = "userName3";
		User user = userDao.selectOne(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void ユーザー登録情報削除() throws Exception {
		String userName = "userName3";
	}


	@Test
	public void userNameユニークチェック_重複あり() throws Exception {

		String userName = "userName3";
		int expected = 1;
<<<<<<< HEAD
	    int actual = userDao.exist(userName);

	    assertThat(expected, is(actual));
=======
		int actual = userDao.exist(userName);

		assertThat(expected, is(actual));
>>>>>>> main
	}

	@Test
	public void userNameユニークチェック_重複なし() throws Exception {

		String userName = "uniqueUserName";
		int expected = 0;
<<<<<<< HEAD
	    int actual = userDao.exist(userName);

	    assertThat(expected, is(actual));
=======
		int actual = userDao.exist(userName);

		assertThat(expected, is(actual));
>>>>>>> main
	}
}
