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

	@PostMapping("/favGift")
	public void create(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

		String userName = httpServletRequest.getRemoteUser();

		favGiftService.create(userName, giftId);

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}

	@PostMapping("/notFavGift")
	public void delete(Model model, @RequestParam("giftId") int giftId, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getRemoteUser();

		favGiftService.delete(userName, giftId);

		String url = "/giftDetail/" + giftId;

		response.sendRedirect(url);
	}
}