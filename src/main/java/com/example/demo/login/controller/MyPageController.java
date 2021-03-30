package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.service.FavGiftService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class MyPageController {

	@Autowired
	UserService userService;

	@Autowired
	FavGiftService favGiftService;

	@GetMapping("/mypage")
	public String show() {
		return "mypage/mypage";
	}
}