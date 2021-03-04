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

		String userName = "userName";
		int giftId = 100;

		boolean expected = true;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り登録失敗() throws Exception {

		String userName = "userName";
		int giftId = 9999;

		boolean expected = false;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 登録済お気に入り削除成功() throws Exception {

		String userName = "userName";
		int giftId = 1;

		boolean expected = true;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録お気に入り削除失敗() throws Exception {

		String userName = "userName";
		int giftId = 101;

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録giftId_お気に入り削除失敗() throws Exception {

		String userName = "userName";
		int giftId = 9999;

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り件数() throws Exception {

		String userName = "userName";

		int expected = 1;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り未登録_お気に入り件数() throws Exception {

		String userName = "userName2";

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId発行済み() throws Exception {

		String userName = "userName";
		int giftId = 1;

		boolean expected = true;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId未発行() throws Exception {

		String userName = "userName";
		int giftId = 101;

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り一覧() throws Exception {

		String userName = "userName";

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		assertThat(allFavGifts, hasItems(hasProperty("favId", is(0))));
		assertThat(allFavGifts, hasItems(hasProperty("userId", is(1))));
		assertThat(allFavGifts, hasItems(hasProperty("giftId", is(1))));
		assertThat(allFavGifts, hasItems(hasProperty("guestName", is("掛布雅之"))));
		assertThat(allFavGifts, hasItems(hasProperty("giftName", is("ロールケーキ"))));
		assertThat(allFavGifts, hasItems(hasProperty("price", is("1000円"))));
		assertThat(allFavGifts, hasItems(hasProperty("image", is("1.jpg"))));
		assertThat(allFavGifts, hasItems(hasProperty("shop", is("Flat"))));
		assertThat(allFavGifts, hasItems(hasProperty("address", is("豊中市上野東3-18-8"))));
		assertThat(allFavGifts, hasItems(hasProperty("phone", is("06-6848-7505"))));
	}



}
