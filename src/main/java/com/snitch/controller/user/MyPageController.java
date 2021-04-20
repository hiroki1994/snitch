package com.snitch.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/users/mypage")
    public String display() {
	return "mypage/mypage";
    }
}