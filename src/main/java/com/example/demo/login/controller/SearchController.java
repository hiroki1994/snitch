package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.model.SearchForm;
import com.example.demo.login.domain.service.GiftService;

@Controller
public class SearchController {

	@Autowired
	GiftService giftService;

	@GetMapping("/search")
	public String postSearchGift(@Validated SearchForm form,  BindingResult bindingResult, Model model, @RequestParam("keyword") String keyword) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("searchForm", form);
			return "searchResult/searchResult";
		}

		List<Gift> giftList = giftService.search(keyword);

		model.addAttribute("giftList", giftList);

		int count = giftService.count(keyword);

		model.addAttribute("giftListCount", count);

		return "searchResult/searchResult";
	}
}

