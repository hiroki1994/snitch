package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.SecurityConfig;
import com.example.demo.login.domain.service.FavGiftService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class WithdrawController {

	@Autowired
	UserService userService;

	@Autowired
	FavGiftService favGiftService;

	@GetMapping("/mypage/withdrawal")
	public String show() {
		return "mypage/withdrawal/withdrawal";
	}

	@DeleteMapping("/mypage/users")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		favGiftService.deleteMany(userName);

		userService.delete(userName);

		SecurityConfig.autoLogout(request, response);
	}
}
