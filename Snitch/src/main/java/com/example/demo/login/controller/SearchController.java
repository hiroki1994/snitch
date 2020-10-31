package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.model.Omiyage;
import com.example.demo.login.domain.model.SearchForm;
import com.example.demo.login.domain.service.OmiyageService;

//検索用コントローラクラス
@Controller
public class SearchController {

	@Autowired
	OmiyageService omiyageService;

	//検索実行メソッド
	@GetMapping("/search") //バインディングとバリデーションを実施  「tenplate.html」の検索フォーム内のname属性「keyword」をパラメータとして取得
	public String postSearchOmiyage(@Validated SearchForm form,  BindingResult bindingResult, Model model, @RequestParam("keyword") String keyword) {

		//バインディングでエラー発生(バリデーションエラー含む)した場合、「searchResult.html」へ遷移
		if(bindingResult.hasErrors()) {
			model.addAttribute("searchForm", form);
			return "searchResult/searchResult";

		}

		//検索キーワードが含まれるお土産を検索　リストに格納
		List<Omiyage> omiyageList = omiyageService.search(keyword);

		//検索結果を画面表示させるためにmodelオブジェクト「omiyageList」に取得した値をセット
		model.addAttribute("omiyageList", omiyageList);

		//検索結果件数取得
		int count = omiyageService.count(keyword);

		//検索結果件数を画面表示させるためにmodelオブジェクト「omiyageListCount」に取得した値をセット
		model.addAttribute("omiyageListCount", count);

		//「searchResult.html」へ遷移
		return "searchResult/searchResult";
	}
}

