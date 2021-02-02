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

@Controller
public class GiftDetailController {

	@Autowired
	GiftService giftService;

	@Autowired
	FavGiftService favGiftService;

	@GetMapping("/giftDetail/{id}")
	public String getgiftDetail(@ModelAttribute GiftDetail detail, Model model, @PathVariable("id") int giftId, HttpServletRequest httpServletRequest) {

			Gift gift = giftService.selectOne(giftId);

			detail.setGiftId(gift.getGiftId());
			detail.setGuestName(gift.getGuestName());
			detail.setGiftName(gift.getGiftName());
			detail.setPrice(gift.getPrice());
			detail.setImage(gift.getImage());
			detail.setDescription(gift.getDescription());
			detail.setShop(gift.getShop());
			detail.setAddress(gift.getAddress());
			detail.setPhone(gift.getPhone());

			model.addAttribute("giftDetail", detail);

			String userName = httpServletRequest.getRemoteUser();

			System.out.println("ログインしているんは"+userName);
			int favIdResult = favGiftService.searchFavId(userName, giftId);

			model.addAttribute("favIdResultModel", favIdResult);

		return "giftDetail/giftDetail";
	}
}

