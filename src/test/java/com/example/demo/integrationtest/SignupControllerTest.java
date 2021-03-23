package com.example.demo.integrationtest;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.UserForm;




@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void signup_suceess() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("uniqueUserName");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");


		mockMvc.perform(post("/signupUser").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	public void signup_fail_UsernameUniqueError() throws Exception {

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
	public void signup_fail_ValidationError() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("ああ");
		form.setMailAddress("mail");
		form.setPassword("いい");

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
