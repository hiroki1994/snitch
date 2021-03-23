package com.example.demo;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;




@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/Delete.sql", "/Schema.sql", "/Insert.sql"})
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void 新規登録成功() throws Exception {

		User user = new User();

		user.setUserName("uniqueUserName");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		boolean expected = true;
		boolean actual = userService.insertOne(user);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void 新規登録失敗() throws Exception {

		User user = new User();

		user.setUserName("userName3");
		user.setMailAddress("mail@gmail.com");
		user.setPassword("7777");

		boolean expected = false;
		boolean actual = userService.insertOne(user);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void 登録情報取得() throws Exception {

		String userName = "userName3";
		User user = userService.selectOne(userName);

		assertThat(user, hasProperty("userName", equalTo("userName3")));
		assertThat(user, hasProperty("mailAddress", equalTo("mailaddress3@gmail.co.jp")));
		assertThat(user, hasProperty("password", equalTo("$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa")));
	}

	@Test
	public void 登録情報更新成功() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName4");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		boolean expected = true;

		boolean actual = userService.updateOne(user, userName);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void 登録情報更新失敗() throws Exception {

		String userName = "userName3";

		User user = new User();

		user.setUserName("userName5");
		user.setMailAddress("mailaddress3@gmail.co.jp");
		user.setPassword("password2");

		boolean expected = false;

		boolean actual = userService.updateOne(user, userName);

		assertThat(expected, equalTo(actual));
	}

	@Test
	public void ユーザー登録情報削除成功() throws Exception {

		String userName = "userName3";

		boolean expected = true;
		boolean actual = userService.deleteOne(userName);

		assertThat(expected, equalTo(actual));

	}

	@Test
	public void ユーザー登録情報削除失敗() throws Exception {

		String userName = "userName4";

		boolean expected = false;
		boolean actual = userService.deleteOne(userName);

		assertThat(expected, equalTo(actual));

	}


	@Test
	public void userNameユニークチェック_重複あり() throws Exception {

		String userName = "userName3";
		int expected = 1;
		int actual = userService.exist(userName);

		assertThat(expected, is(actual));
	}

	@Test
	public void userNameユニークチェック_重複なし() throws Exception {

		String userName = "uniqueUserName";
		int expected = 0;
		int actual = userService.exist(userName);

		assertThat(expected, is(actual));
	}
}