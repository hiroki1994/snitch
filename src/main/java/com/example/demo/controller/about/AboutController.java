package com.example.demo.controller.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

	@GetMapping("/about")
	public String display() {

		return "about/about";
	}
}
