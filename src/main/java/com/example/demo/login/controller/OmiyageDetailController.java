package com.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.login.domain.model.Omiyage;
import com.example.demo.login.domain.model.OmiyageDetail;
import com.example.demo.login.domain.service.FavOmiyageService;
import com.example.demo.login.domain.service.OmiyageService;

//お土産詳細画面用コントローラ
@Controller
public class OmiyageDetailController {

	@Autowired
	OmiyageService omiyageService;

	@Autowired
	FavOmiyageService favOmiyageService;

	//{id}で指定されたお土産の詳細画面表示
	@GetMapping("/omiyageDetail/{id}") //パスの{id}をパラメータ「omiyaID」として取得
	public String getOmiyageDetail(@ModelAttribute OmiyageDetail detail, Model model, @PathVariable("id") int omiyaID, HttpServletRequest httpServletRequest) {

			//パラメータ「omiyaID」と一致するお土産をテーブル「omiyage」内から取得
			Omiyage omiyage = omiyageService.selectOne(omiyaID);

			//「omiyage」に格納されているデータを「detail」に格納
			detail.setOmiyaID(omiyage.getOmiyaID());
			detail.setGuest(omiyage.getGuest());
			detail.setName(omiyage.getName());
			detail.setPrice(omiyage.getPrice());
			detail.setImage(omiyage.getImage());
			detail.setDescription(omiyage.getDescription());
			detail.setShop(omiyage.getShop());
			detail.setAddress(omiyage.getAddress());
			detail.setPhone(omiyage.getPhone());
			detail.setKeyword(omiyage.getKeyword());

			//画面表示させるためにモデルオブジェクト「omiyageList」に追加
			model.addAttribute("OmiyageDetail", detail);

			//認証済みのユーザーのIDを取得
			String userId = httpServletRequest.getRemoteUser();

			//お気に入り登録時に発行されるユニークID「favId」を検索用に作成
			String favId = userId + omiyage.getOmiyaID();

			//カラム「favId」に指定のIDで登録されているお気に入りを検索 お気に入り未登録の場合nullが返ってくる
			String favIdResult = favOmiyageService.searchFavId(favId);

			//「お気に入り」「お気に入り解除」ボタンの表示切り替えのために、modelオブジェクト「favIdResultModel」に格納
			model.addAttribute("favIdResultModel", favIdResult);

		return "omiyageDetail/omiyageDetail";
	}
}

