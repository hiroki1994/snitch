package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.SignupController;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserForm;
import com.example.demo.login.domain.service.UserService;




@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/Delete.sql", "/Schema.sql", "/Insert.sql"})
public class MyPageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	UserService userService;

	@InjectMocks
	SignupController signupController;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@WithMockUser(username="userName3")
	public void ユーザー登録情報削除() throws Exception {

		String userName = "userName3";

		when(userService.deleteOne(userName)).thenReturn(true);

		mockMvc.perform(post("/deleteUser").with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/login"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報取得() throws Exception {

		User user = new User();

		String userName = "userName3";

		user.setUserName(userName);
		user.setMailAddress("mailaddress3@gmail.co.jp");

		when(userService.selectOne(userName)).thenReturn(user);

		mockMvc.perform(post("/mypage/updateUser").with(csrf()))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("userName3")))
		.andExpect(content().string(containsString("mailaddress3@gmail.co.jp")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新成功() throws Exception {

		User user = new User();

		String userName = "userName3";

		when(userService.updateOne(user, userName)).thenReturn(true);

		UserForm form = new UserForm();

		form.setUserName("uniqueUserName");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新失敗_ユーザーネームユニークエラー() throws Exception {

		User user = new User();

		String userName = "userName3";

		when(userService.updateOne(user, userName)).thenReturn(false);

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
	public void 登録情報更新失敗_バリデーションエラー() throws Exception {

		User user = new User();

		String userName = "userName3";

		when(userService.updateOne(user, userName)).thenReturn(false);

		UserForm form = new UserForm();

		form.setUserName("くに");
		form.setMailAddress("mail");
		form.setPassword("くに");


		mockMvc.perform(post("/signupUser").flashAttr("userForm", form).with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("signup/signup"))
		.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
		.andExpect(content().string(containsString("ユーザーネームは半角英数字で入力してください")))
		.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
		.andExpect(content().string(containsString("メールアドレス形式で入力してください")))
		.andExpect(content().string(containsString("パスワードは3字以上20字以下で入力してください")))
		.andExpect(content().string(containsString("パスワードは半角英数字で入力してください")));
	}
}
