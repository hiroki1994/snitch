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
	public String showMypage() {
		return "mypage/mypage";
	}

	@PostMapping("/mypage/deleteUser")
	public String showDeletePage() {
		return "mypage/deleteUser/deleteUser";
	}

	@PostMapping("/deleteUser")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		favGiftService.deleteMany(userName);

		userService.delete(userName);

		SecurityConfig.autoLogout(request, response);
	}

	@PostMapping("/mypage/updateUser")
	public String showUpdatePage(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {

		String userName = request.getRemoteUser();

		User user = userService.select(userName);

		form.setUserName(user.getUserName());
		form.setMailAddress(user.getMailAddress());

		model.addAttribute("userForm", form);

		return "mypage/updateUser/updateUser";
	}

	@PostMapping("/updateUserInfo")
	public String update(@ModelAttribute @Validated(GroupOrder.class) UserForm form, BindingResult bindingResult,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (bindingResult.hasErrors()) {
			return showUpdatePage(form, model, request);
		}

		String userName = request.getRemoteUser();

		User user = new User();

		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		userService.update(user, userName);

		String newUsername = String.valueOf(form.getUserName());
		String newPassword = String.valueOf(form.getPassword());

		SecurityConfig.autoLogin(request, newUsername, newPassword, response);

		return null;
	}

	@PostMapping("/mypage/favorite")
	public String showFavoritePage(Model model, HttpServletRequest httpServletRequest) {

		String userName = httpServletRequest.getRemoteUser();

		int favIds = favGiftService.count(userName);

		model.addAttribute("favIds", favIds);

		if (favIds == 0) {

			return "mypage/favorite/favorite";
		}

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		model.addAttribute("allFavGifts", allFavGifts);

		return "mypage/favorite/favorite";
	}
}
