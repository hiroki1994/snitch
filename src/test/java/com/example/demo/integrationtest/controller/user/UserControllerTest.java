package com.example.demo.integrationtest.controller.user;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.user.UserForm;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "userName3")
	public void displayUserInfo() throws Exception {

		mockMvc.perform(get("/users/edit")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("userName3")))
				.andExpect(content().string(containsString("mailaddress3@gmail.co.jp")));
	}

	@Test
	@WithMockUser(username = "userName5")
	public void displayUserInfo_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(get("/users/edit")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error/error"));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void updateUserInfo_success() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("uniqueUserName");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(put("/users").flashAttr("userForm", form).with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/users/mypage"));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void updateUserInfo_success_usernameIsEqualToAuthentivatedUserName() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(put("/users").flashAttr("userForm", form)
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/users/mypage"));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void updateUserInfo_fail_usernameUniqueError() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("userName4");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(put("/users").flashAttr("userForm", form)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void updateUserInfo_fail_validationError() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("ああ");
		form.setMailAddress("mail");
		form.setPassword("いい");

		mockMvc.perform(put("/users").flashAttr("userForm", form)
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("mypage/edit_user/edit_user"))
				.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("ユーザーネームは半角英数字で入力してください")))
				.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("メールアドレス形式で入力してください")))
				.andExpect(content().string(containsString("パスワードは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("パスワードは半角英数字で入力してください")));
	}

	@Test
	public void displayRegistrationPage() throws Exception {

		mockMvc.perform(get("/users/new")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ユーザー登録画面")));
	}


	@Test
	public void registration_suceess() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("uniqueUserName");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/users").flashAttr("userForm", form).with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/users/mypage"));
	}

	@Test
	public void registration_fail_usernameUniqueError() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/users").flashAttr("userForm", form).with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("registration/registration"))
				.andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
	public void registration_fail_ValidationError() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("ああ");
		form.setMailAddress("mail");
		form.setPassword("いい");

		mockMvc.perform(post("/users").flashAttr("userForm", form).with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("registration/registration"))
				.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("ユーザーネームは半角英数字で入力してください")))
				.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("メールアドレス形式で入力してください")))
				.andExpect(content().string(containsString("パスワードは3字以上20字以下で入力してください")))
				.andExpect(content().string(containsString("パスワードは半角英数字で入力してください")));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void displayWithdrawalPage() throws Exception {

		mockMvc.perform(get("/users/withdrawal")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("本当に退会してもよろしいでしょうか?")));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void deleteUser_success() throws Exception {

		mockMvc.perform(delete("/users")
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/users/session/login"));
	}

	@Test
	@WithMockUser(username = "userName5")
	public void deleteUser_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(delete("/users")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error/error"));
	}
}
