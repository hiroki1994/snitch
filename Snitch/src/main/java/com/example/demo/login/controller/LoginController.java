package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//ログイン機能用コントローラークラス
@Controller
public class LoginController {


	@GetMapping("/login")
	public String getLogin() {

		//ログインページ「login.html」を表示
		return "login/login";
	}
}
