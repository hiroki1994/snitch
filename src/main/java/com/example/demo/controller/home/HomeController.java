package com.example.demo.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.service.GiftService;
import com.example.demo.domain.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    GiftService giftService;

    @GetMapping("/")
    public String display(Model model) {

	List<Gift> gifts = giftService.selectMany();
	model.addAttribute("gifts", gifts);

	return "home/home";
    }
}