package com.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.model.GiftDetail;
import com.example.demo.login.domain.service.FavGiftService;
import com.example.demo.login.domain.service.GiftService;

//お土産詳細画面用コントローラ
@Controller
public class GiftDetailController {

	@Autowired
	GiftService giftService;

	@Autowired
	FavGiftService favGiftService;

	//{id}で指定されたお土産の詳細画面表示
	@GetMapping("/giftDetail/{id}") //パスの{id}をパラメータ「giftId」として取得
	public String getgiftDetail(@ModelAttribute GiftDetail detail, Model model, @PathVariable("id") int giftId, HttpServletRequest httpServletRequest) {

			//パラメータ「giftId」と一致するお土産をテーブル「gift」内から取得
			Gift gift = giftService.selectOne(giftId);

			//「gift」に格納されているデータを「detail」に格納
			detail.setGiftId(gift.getGiftId());
			detail.setGuestName(gift.getGuestName());
			detail.setGiftName(gift.getGiftName());
			detail.setPrice(gift.getPrice());
			detail.setImage(gift.getImage());
			detail.setDescription(gift.getDescription());
			detail.setShop(gift.getShop());
			detail.setAddress(gift.getAddress());
			detail.setPhone(gift.getPhone());

			//画面表示させるためにモデルオブジェクト「giftList」に追加
			model.addAttribute("giftDetail", detail);

			//認証済みのユーザーのIDを取得
			String userId = httpServletRequest.getRemoteUser();



			String favIdResult = favGiftService.searchFavId(userId, giftId);

			//「お気に入り」「お気に入り解除」ボタンの表示切り替えのために、modelオブジェクト「favIdResultModel」に格納
			model.addAttribute("favIdResultModel", favIdResult);

		return "giftDetail/giftDetail";
	}
}

