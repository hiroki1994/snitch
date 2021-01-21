package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.service.FavGiftService;


//お気に入り機能コントローラークラス
@Controller
public class FavGiftController {

	@Autowired
	FavGiftService favGiftService;

	//お気に入り追加用コントローラー
	@PostMapping("/addGift")
	public void getFavGiftAdd(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {


		//ログイン中のユーザー名の取得
		String userName = httpServletRequest.getRemoteUser();

		//お土産個別のID「giftId」と「userId」を引数にサービスクラスへ処理を投げる
		boolean result = favGiftService.insert(userName, giftId);

		//お気に入り新規登録の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録成功");
		} else {
			System.out.println("お気に入り登録失敗");
		}

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}

	//お気に入り登録解除用コントローラー
	@PostMapping("/deleteFavGift")
	public void getFavGiftDelite(Model model, @RequestParam("giftId") int giftId, HttpServletRequest request, HttpServletResponse response) throws IOException {


		//ログイン中のユーザー名の取得
		String userName = request.getRemoteUser();



		//作成したユニークIDを引数にサービスクラスへ処理を投げる
		boolean result = favGiftService.delete(userName, giftId);

		//お気に入り登録解除の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録解除完了");
		} else {
			System.out.println("お気に入り登録解除成功");
		}

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}
}

