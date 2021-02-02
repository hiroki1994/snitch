package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutSnitchController {

	@GetMapping("/aboutsnitch")
	public String getSnitch() {

		return "about/aboutSnitch";
	}
}
