package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.service.FavOmiyageService;


//お気に入り機能コントローラークラス
@Controller
public class FavOmiyageController {

	@Autowired
	FavOmiyageService favOmiyageService;

	//お気に入り追加用コントローラー
	@PostMapping("/addOmiyage")
	public void postFavOmiyageAdd(Model model, @RequestParam("favOmiyageId") int favOmiyaID, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

		//ログイン中のユーザー名の取得
		String userId = httpServletRequest.getRemoteUser();

		//お土産個別のID「favOmiyaID(omiyaID)」と「userID」を引数にサービスクラスへ処理を投げる
		boolean result = favOmiyageService.insert(favOmiyaID, userId);

		//お気に入り新規登録の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録成功");
		} else {
			System.out.println("お気に入り登録失敗");
		}

		String url = "/omiyageDetail/" + favOmiyaID;
		response.sendRedirect(url);
	}

	//お気に入り登録解除用コントローラー
	@PostMapping("/deleteFavOmiyage")
	public void postFavOmiyageDelite(Model model, @RequestParam("favOmiyageId") String favOmiyaID, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

		//ログイン中のユーザー名の取得
		String userId = httpServletRequest.getRemoteUser();

		//ユーザーIDとお土産のIDを連結し、テーブルに登録されたお気に入り済みのお土産を判別するユニークIDを作成
		String favId = userId + favOmiyaID;
		//作成したユニークIDを引数にサービスクラスへ処理を投げる
		boolean result = favOmiyageService.delete(favId);

		//お気に入り登録解除の結果をコンソールに表示
		if(result == true) {
			System.out.println("お気に入り登録解除完了");
		} else {
			System.out.println("お気に入り登録解除成功");
		}

		String url = "/omiyageDetail/" + favOmiyaID;
		//お土産詳細画面のコントローラーへ遷移
		response.sendRedirect(url);
	}
}

