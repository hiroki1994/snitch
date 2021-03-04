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
public class SignupControllerTest {

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
	public void 新規登録成功() throws Exception {

		User user = new User();

		when(userService.insertOne(user)).thenReturn(true);

		UserForm form = new UserForm();

		form.setUserName("uniqueUserName");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");


		mockMvc.perform(post("/signupUser").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	public void 新規登録失敗_ユーザーネームユニークエラー() throws Exception {

		User user = new User();

		when(userService.insertOne(user)).thenReturn(false);

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/signupUser").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("signup/signup"))
			.andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
	public void 新規登録失敗_バリデーションエラー() throws Exception {

		User user = new User();

		when(userService.insertOne(user)).thenReturn(false);

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
