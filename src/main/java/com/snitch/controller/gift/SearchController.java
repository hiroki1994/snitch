package com.snitch.controller.gift;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snitch.domain.model.gift.Gift;
import com.snitch.domain.model.searchform.SearchForm;
import com.snitch.domain.service.GiftService;

@Controller
public class SearchController {

    @Autowired
    GiftService giftService;

    @GetMapping("/gifts/search")
    public String search(@Validated SearchForm form, BindingResult bindingResult, Model model,
	    @RequestParam("keyword") String keyword) {

	if (bindingResult.hasErrors()) {
	    model.addAttribute("searchForm", form);
	    return "search_result/search_result";
	}

	int giftCount = giftService.count(keyword);
	model.addAttribute("giftCount", giftCount);

	if (giftCount == 0) {
	    return "search_result/search_result";
	}

	List<Gift> gifts = giftService.search(keyword);
	model.addAttribute("gifts", gifts);

	return "search_result/search_result";
    }
}