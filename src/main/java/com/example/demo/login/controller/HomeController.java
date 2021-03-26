package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.service.GiftService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	GiftService giftService;

	@GetMapping("/home")
	public String showHomePage(Model model) {

		List<Gift> selectedGifts = giftService.selectMany();

		model.addAttribute("giftList", selectedGifts);

		return "home/home";
	}
}
