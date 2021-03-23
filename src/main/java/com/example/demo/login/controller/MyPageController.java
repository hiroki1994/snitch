package com.example.demo.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.SecurityConfig;
import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserForm;
import com.example.demo.login.domain.service.FavGiftService;
import com.example.demo.login.domain.service.UserService;


@Controller
public class MyPageController {

	@Autowired
	UserService userService;

	@Autowired
	FavGiftService favGiftService;

	@GetMapping("/mypage")
	public String getMypage() {
		return "mypage/mypage";
	}

	@PostMapping("/mypage/deleteUser")
	public String getDelete() {
		return "mypage/deleteUser/deleteUser";
	}

	@PostMapping("/deleteUser")
	public void postDeleteUser( HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		boolean resultDeleteFav = favGiftService.deleteMany(userName);

		if(resultDeleteFav) {
			System.out.println("お気に入り全件削除成功");
		} else {
			System.out.println("お気に入り全件削除失敗");
		}

		boolean resultDeleteUser = userService.deleteOne(userName);

		if(resultDeleteUser) {
			System.out.println("ユーザー削除成功");
		} else {
			System.out.println("ユーザー削除失敗");
		}

		SecurityConfig.autoLogout(request, response);
	}

	@PostMapping("/mypage/updateUser")
	public String postUserUpdatePage(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {

		String userName = request.getRemoteUser();

		User user = userService.selectOne(userName);

		form.setUserName(user.getUserName());
		form.setMailAddress(user.getMailAddress());

		model.addAttribute("userForm", form);

		return "mypage/updateUser/updateUser";
	}

	@PostMapping("/updateUserInfo")
	public String postUserUpdate(@ModelAttribute @Validated(GroupOrder.class)UserForm form, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (bindingResult.hasErrors()) {
			return postUserUpdatePage(form, model, request);
		}

		String userName = request.getRemoteUser();

		User user = new User();

		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		boolean result = userService.updateOne(user, userName);

		if(result) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}

		String newUsername = String.valueOf(form.getUserName());
		String newPassword = String.valueOf(form.getPassword());

		SecurityConfig.autoLogin(request, newUsername, newPassword, response);

		return null;
	}

	@PostMapping("/mypage/favorite")
	public String getFavorite(Model model, HttpServletRequest httpServletRequest) {

		String userName = httpServletRequest.getRemoteUser();

		int favIds = favGiftService.count(userName);

		model.addAttribute("favIds", favIds);

		if(favIds == 0) {

			return "mypage/favorite/favorite";
		}

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		model.addAttribute("allFavGifts", allFavGifts);

		return "mypage/favorite/favorite";
	}
}
