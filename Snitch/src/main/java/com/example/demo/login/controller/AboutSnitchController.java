package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//「AboutSnitch」表示用コントローラークラス
@Controller
public class AboutSnitchController {


	@GetMapping("/aboutsnitch")
	public String getSnitch() {

		//サイト紹介ページ「aboutSnitch.html」へ遷移
		return "about/aboutSnitch";
	}
}
