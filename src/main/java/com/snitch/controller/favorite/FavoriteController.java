package com.snitch.controller.favorite;

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

import com.snitch.domain.model.favorite.Favorite;
import com.snitch.domain.service.FavoriteService;

@Controller
public class FavoriteController {

    @Autowired
    FavoriteService favGiftService;

    @PostMapping("/favorites")
    public void createOne(Model model, @RequestParam("giftId") int giftId, HttpServletRequest httpServletRequest,
	    HttpServletResponse response) throws IOException {

	String userName = httpServletRequest.getRemoteUser();
	favGiftService.createOne(userName, giftId);

	String url = "/gifts/" + giftId;
	response.sendRedirect(url);
    }

    @DeleteMapping("/favorites")
    public void deleteOne(Model model, @RequestParam("giftId") int giftId, HttpServletRequest request,
	    HttpServletResponse response) throws IOException {

	String userName = request.getRemoteUser();
	favGiftService.deleteOne(userName, giftId);

	String url = "/gifts/" + giftId;
	response.sendRedirect(url);
    }

    @GetMapping("/favorites")
    public String display(Model model, HttpServletRequest httpServletRequest) {

	String userName = httpServletRequest.getRemoteUser();
	int favoriteIds = favGiftService.count(userName);
	model.addAttribute("favoriteIds", favoriteIds);

	if (favoriteIds == 0) {
	    return "mypage/favorite/favorite";
	}

	List<Favorite> favorites = favGiftService.selectAll(userName);
	model.addAttribute("favorites", favorites);

	return "mypage/favorite/favorite";
    }
}