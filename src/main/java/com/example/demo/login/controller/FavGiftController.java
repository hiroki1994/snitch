package com.example.demo.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.service.FavGiftService;


@Controller
public class FavGiftController {

	@Autowired
	FavGiftService favGiftService;

	@PostMapping("/favGifts")
	public void create(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

		String userName = httpServletRequest.getRemoteUser();

		favGiftService.create(userName, giftId);

		String url = "/gifts/" + giftId;

		response.sendRedirect(url);
	}

	@DeleteMapping("/favGifts")
	public void delete(Model model, @RequestParam("giftId") int giftId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		favGiftService.delete(userName, giftId);

		String url = "/gifts/" + giftId;

		response.sendRedirect(url);
	}

	@GetMapping("/mypage/favGifts")
	public String show(Model model, HttpServletRequest httpServletRequest) {

		String userName = httpServletRequest.getRemoteUser();

		int favIds = favGiftService.count(userName);

		model.addAttribute("favIds", favIds);

		if (favIds == 0) {

			return "mypage/favorite/favorite";
		}

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		model.addAttribute("allFavGifts", allFavGifts);

		return "mypage/favorite/favorite";
	}
}