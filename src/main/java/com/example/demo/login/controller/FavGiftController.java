package com.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.service.FavGiftService;


//お気に入り機能コントローラークラス
@Controller
public class FavGiftController {

	@Autowired
	FavGiftService favGiftService;

	//お気に入り追加用コントローラー
	@GetMapping("/addGift")
	public String getFavGiftAdd(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest) {

		//ログイン中のユーザー名の取得
		String userId = httpServletRequest.getRemoteUser();

		//お土産個別のID「giftId」と「userId」を引数にサービスクラスへ処理を投げる
		boolean result = favGiftService.insert(userId, giftId);

		//お気に入り新規登録の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録成功");
		} else {
			System.out.println("お気に入り登録失敗");
		}

		//お土産詳細画面のコントローラーへ遷移
		return "forward:/giftDetail/"+ giftId;
	}

	//お気に入り登録解除用コントローラー
	@GetMapping("/deleteFavGift")
	public String getFavGiftDelite(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest) {

		//ログイン中のユーザー名の取得
		String userId = httpServletRequest.getRemoteUser();



		//作成したユニークIDを引数にサービスクラスへ処理を投げる
		boolean result = favGiftService.delete(userId, giftId);

		//お気に入り登録解除の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録解除完了");
		} else {
			System.out.println("お気に入り登録解除成功");
		}

		//お土産詳細画面のコントローラーへ遷移
		return "forward:/giftDetail/"+giftId;
	}
}

