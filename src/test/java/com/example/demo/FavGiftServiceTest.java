package com.example.demo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.service.FavGiftService;



@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class FavGiftServiceTest {

	@Autowired
	FavGiftService favGiftService;

	@Test
	public void お気に入り登録成功() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		boolean expected = true;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り登録失敗() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		boolean expected = false;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 登録済お気に入り削除成功() throws Exception {

		String userName = "userName3";
		int giftId = 1001;

		boolean expected = true;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録お気に入り削除失敗() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録giftId_お気に入り削除失敗() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り件数() throws Exception {

		String userName = "userName3";

		int expected = 2;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り未登録_お気に入り件数() throws Exception {

		String userName = "userName4";

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId発行済み() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		boolean expected = true;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId未発行() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り一覧() throws Exception {

		String userName = "userName3";

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		assertThat(allFavGifts, hasItems(hasProperty("favId", is(0))));
		assertThat(allFavGifts, hasItems(hasProperty("userId", is(1))));
		assertThat(allFavGifts, hasItems(hasProperty("giftId", is(1000))));
		assertThat(allFavGifts, hasItems(hasProperty("guestName", is("中越典子"))));
		assertThat(allFavGifts, hasItems(hasProperty("giftName", is("マカロン"))));
		assertThat(allFavGifts, hasItems(hasProperty("price", is("120個入　3938円"))));
		assertThat(allFavGifts, hasItems(hasProperty("image", is("1000.jpg"))));
		assertThat(allFavGifts, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
		assertThat(allFavGifts, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
		assertThat(allFavGifts, hasItems(hasProperty("phone", is("03-3352-1111"))));
	}
}
