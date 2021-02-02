package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.login.domain.service.FavGiftService;


@Controller
public class FavGiftController {

	@Autowired
	FavGiftService favGiftService;

	@PostMapping("/addGift")
	public void getFavGiftAdd(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

		String userName = httpServletRequest.getRemoteUser();

		boolean result = favGiftService.insert(userName, giftId);

		if(result == true) {
			System.out.println("お気に入り登録成功");
		} else {
			System.out.println("お気に入り登録失敗");
		}

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}

	@PostMapping("/deleteFavGift")
	public void getFavGiftDelite(Model model, @RequestParam("giftId") int giftId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		boolean result = favGiftService.delete(userName, giftId);

		if(result == true) {
			System.out.println("お気に入り登録解除完了");
		} else {
			System.out.println("お気に入り登録解除成功");
		}

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}
}

