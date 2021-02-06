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
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
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
	public void postDeleteUser( HttpServletRequest request, HttpServletResponse response) {

		String userName = request.getRemoteUser();

		System.out.println(userName +"の登録情報を削除します");

	    boolean result = userService.deleteOne(userName);

	    if(result == true) {
	    	System.out.println("削除成功");
	    } else {
	    	System.out.println("削除失敗");
	    }

	    try {
			SecurityConfig.autoLogout(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/mypage/updateUser")
	public String postUserUpdatePage(@ModelAttribute SignupForm form, Model model, HttpServletRequest request) {

		String userName = request.getRemoteUser();

		System.out.println(userName+"の登録情報を更新します");

		if(userName != null && userName.length() > 0) {
			User user = userService.selectOne(userName);
			form.setUserName(user.getUserName());
			form.setMailAddress(user.getMailAddress());

			model.addAttribute("signupForm", form);
		}


		return "mypage/updateUser/updateUser";
    }

	@PostMapping("/updateUserInfo")
	public String postUserUpdate(@ModelAttribute @Validated(GroupOrder.class)SignupForm form, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		if (bindingResult.hasErrors()) {
			return postUserUpdatePage(form, model, request);

		}

		String userName_LoggedIn = request.getRemoteUser();

		System.out.println("ログインしているのは"+ userName_LoggedIn);

		User user = new User();

		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		boolean result = userService.updateOne(user, userName_LoggedIn);

		if(result == true) {
		   System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}

		try {
			String username = String.valueOf(form.getUserName());
			String password = String.valueOf(form.getPassword());
			//SecurityContextHolder.clearContext();
			SecurityConfig.autoLogin(request, username, password, response);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "mypage/mypage";
	}

	@PostMapping("/mypage/favorite")
	public String getFavorite(Model model, HttpServletRequest httpServletRequest) {

		String userName = httpServletRequest.getRemoteUser();

		List<FavGift> allfavGifts = favGiftService.selectAll(userName);

		model.addAttribute("allfavGifts", allfavGifts);

		int number = favGiftService.count(userName);

		model.addAttribute("number", number);

		return "mypage/favorite/favorite";
	}
}
