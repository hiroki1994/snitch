package com.snitch.controller.gift;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.snitch.domain.model.gift.Gift;
import com.snitch.domain.model.gift.GiftDetail;
import com.snitch.domain.service.FavoriteService;
import com.snitch.domain.service.GiftService;

@Controller
public class GiftController {

    @Autowired
    GiftService giftService;

    @Autowired
    FavoriteService favGiftService;

    @GetMapping("/gifts/{id}")
    public String display(@ModelAttribute GiftDetail detail, Model model, @PathVariable("id") int giftId,
	    HttpServletRequest httpServletRequest) {

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
	boolean result = favGiftService.exist(userName, giftId);
	model.addAttribute("result", result);

	return "gift_detail/gift_detail";
    }
}