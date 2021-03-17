package com.example.demo;


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

import com.example.demo.login.domain.model.UserForm;




@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MyPageControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username="userName3")
	public void ユーザー登録情報削除() throws Exception {

		mockMvc.perform(post("/deleteUser").with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/login"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報取得() throws Exception {

		mockMvc.perform(post("/mypage/updateUser").with(csrf()))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("userName3")))
			.andExpect(content().string(containsString("mailaddress3@gmail.co.jp")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新成功() throws Exception {

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
	public void 登録情報更新成功_認証済みユーザーネームと同名() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新失敗_ユーザーネームユニークエラー() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("userName4");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新失敗_バリデーションエラー() throws Exception {

		UserForm form = new UserForm();

		form.setUserName("ああ");
		form.setMailAddress("mail");
		form.setPassword("いい");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/updateUser/updateUser"))
			.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
			.andExpect(content().string(containsString("ユーザーネームは半角英数字で入力してください")))
			.andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
			.andExpect(content().string(containsString("メールアドレス形式で入力してください")))
			.andExpect(content().string(containsString("パスワードは3字以上20字以下で入力してください")))
			.andExpect(content().string(containsString("パスワードは半角英数字で入力してください")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void お気に入り一覧() throws Exception {

		String userName = "userName3";

		mockMvc.perform(post("/mypage/favorite").param("userName", userName).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")))
			.andExpect(content().string(containsString("マカロン")));
	}
}
