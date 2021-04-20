package com.snitch.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/users/session/login")
    public String display() {
	return "login/login";
    }
}