package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Omiyage;
import com.example.demo.login.domain.service.OmiyageService;
import com.example.demo.login.domain.service.UserService;

//ホーム画面とログアウト機能用のコントローラークラス
@Controller
public class HomeController {


	@Autowired
	UserService userService;

	@Autowired
	OmiyageService omiyageService;


	//ホーム画面用GETメソッド
	@GetMapping("/home")
	public String getHome(Model model) {

		//取得したデータをリスト「Omiyage」に格納
		List<Omiyage> omiyageList = omiyageService.selectMany();

		//画面表示させるためにモデルオブジェクト「omiyageList」に追加
		model.addAttribute("omiyageList", omiyageList);

		return "home/home";
	}


    //ログアウト用POSTメソッド
	@PostMapping("/logout")
	public String postLogout() {
		return "redirect:login";
	}



}
