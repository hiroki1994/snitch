package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> main
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
<<<<<<< HEAD
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.SignupController;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserForm;
=======
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.MyPageController;
import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserForm;
import com.example.demo.login.domain.service.FavGiftService;
>>>>>>> main
import com.example.demo.login.domain.service.UserService;



<<<<<<< HEAD

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/Delete.sql", "/Schema.sql", "/Insert.sql"})
=======
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
>>>>>>> main
public class MyPageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	UserService userService;

<<<<<<< HEAD
	@InjectMocks
	SignupController signupController;

	@BeforeEach
	public void initMocks() {
=======
	@Mock
	FavGiftService favGiftService;

	@InjectMocks
	MyPageController myPageController;

	@BeforeEach
	public void initMock() {
>>>>>>> main
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@WithMockUser(username="userName3")
	public void ユーザー登録情報削除() throws Exception {

		String userName = "userName3";

		when(userService.deleteOne(userName)).thenReturn(true);

		mockMvc.perform(post("/deleteUser").with(csrf()))
<<<<<<< HEAD
			   .andExpect(status().isFound())
			   .andExpect(redirectedUrl("/login"));
=======
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/login"));
>>>>>>> main
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
<<<<<<< HEAD
			   .andExpect(status().isOk())
			   .andExpect(content().string(containsString("userName3")))
			   .andExpect(content().string(containsString("mailaddress3@gmail.co.jp")));
=======
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("userName3")))
			.andExpect(content().string(containsString("mailaddress3@gmail.co.jp")));
>>>>>>> main
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
<<<<<<< HEAD
		   	   .andExpect(status().isFound())
		       .andExpect(redirectedUrl("/mypage"));
=======
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
>>>>>>> main
	}

	@Test
	@WithMockUser(username="userName3")
<<<<<<< HEAD
	public void 登録情報更新失敗_ユーザーネームユニークエラー() throws Exception {
=======
	public void 登録情報更新成功_認証済みユーザーネームと同名() throws Exception {
>>>>>>> main

		User user = new User();

		String userName = "userName3";

<<<<<<< HEAD
		when(userService.updateOne(user, userName)).thenReturn(false);
=======
		when(userService.updateOne(user, userName)).thenReturn(true);
>>>>>>> main

		UserForm form = new UserForm();

		form.setUserName("userName3");
		form.setMailAddress("mail@gmail.com");
		form.setPassword("7777");

		mockMvc.perform(post("/updateUserInfo").flashAttr("userForm", form).with(csrf()))
<<<<<<< HEAD
			   .andExpect(status().isOk())
			   .andExpect(content().string(containsString("入力されたユーザーネームは既に使用されています")));
	}

	@Test
=======
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void 登録情報更新失敗_ユーザーネームユニークエラー() throws Exception {

		User user = new User();

		String userName = "userName4";

		when(userService.updateOne(user, userName)).thenReturn(false);

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
>>>>>>> main
	public void 登録情報更新失敗_バリデーションエラー() throws Exception {

		User user = new User();

		String userName = "userName3";

		when(userService.updateOne(user, userName)).thenReturn(false);

		UserForm form = new UserForm();

		form.setUserName("くに");
		form.setMailAddress("mail");
		form.setPassword("くに");


<<<<<<< HEAD
		mockMvc.perform(post("/signupUser").flashAttr("userForm", form).with(csrf()))
			   .andExpect(status().isOk())
			   .andExpect(view().name("signup/signup"))
			   .andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
			   .andExpect(content().string(containsString("ユーザーネームは半角英数字で入力してください")))
			   .andExpect(content().string(containsString("ユーザーネームは3字以上20字以下で入力してください")))
			   .andExpect(content().string(containsString("メールアドレス形式で入力してください")))
			   .andExpect(content().string(containsString("パスワードは3字以上20字以下で入力してください")))
			   .andExpect(content().string(containsString("パスワードは半角英数字で入力してください")));
=======
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

		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftService.selectAll(userName)).thenReturn(allFavGifts);
		when(favGiftService.count(userName)).thenReturn(3);

		mockMvc.perform(post("/mypage/favorite").param("userName", userName).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")));
>>>>>>> main
	}
}
