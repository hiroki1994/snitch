package com.example.demo.controller.gift;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.model.gift.GiftDetail;
import com.example.demo.domain.service.FavGiftService;
import com.example.demo.domain.service.GiftService;

@Controller
public class GiftController {

	@Autowired
	GiftService giftService;

	@Autowired
	FavGiftService favGiftService;

	@GetMapping("/gifts/{id}")
	public String display(@ModelAttribute GiftDetail detail, Model model, @PathVariable("id") int giftId, HttpServletRequest httpServletRequest) {

		Gift gift = giftService.selectOne(giftId);

		detail.setGiftId(gift.getGiftId());
		detail.setRecommenderName(gift.getRecommenderName());
		detail.setGiftName(gift.getGiftName());
		detail.setPrice(gift.getPrice());
		detail.setImage(gift.getImage());
		detail.setDescription(gift.getDescription());
		detail.setShop(gift.getShop());
		detail.setAddress(gift.getAddress());
		detail.setPhone(gift.getPhone());

		model.addAttribute("giftDetail", detail);

		String userName = httpServletRequest.getRemoteUser();

		boolean result = favGiftService.existFavId(userName, giftId);

		model.addAttribute("result", result);

		return "gift_detail/gift_detail";
	}
}