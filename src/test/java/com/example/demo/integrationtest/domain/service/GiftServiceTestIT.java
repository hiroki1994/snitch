package com.example.demo.integrationtest.domain.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.service.GiftService;



@SpringBootTest
@Transactional
public class GiftServiceTestIT {

	@Autowired
	GiftService giftService;

	@Test
	public void countGiftByKeyword() {

		String keyword = "マカロン";

		int expected = 2;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void countGiftByKeyword_zero() {

		String keyword = "H#4kこ";

		int expected = 0;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void searchGift_found() {
		String keyword = "マカロン";

		List<Gift> selectedGifts = giftService.search(keyword);

		assertThat(selectedGifts, hasItems(hasProperty("giftId", is(1000))));
		assertThat(selectedGifts, hasItems(hasProperty("guestName", is("中越典子"))));
		assertThat(selectedGifts, hasItems(hasProperty("giftName", is("マカロン"))));
		assertThat(selectedGifts, hasItems(hasProperty("price", is("120個入　3938円"))));
		assertThat(selectedGifts, hasItems(hasProperty("image", is("1000.jpg"))));
		assertThat(selectedGifts, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
		assertThat(selectedGifts, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
		assertThat(selectedGifts, hasItems(hasProperty("phone", is("03-3352-1111"))));
	}

	@Test
	public void searchGift_notFound() {
		String keyword = "H#4kこ";

		List<Gift> selectedGifts = giftService.search(keyword);

		assertThat(selectedGifts, is(empty()));
	}

	@Test
	public void listGifts() {

		List<Gift> selectedGifts = giftService.selectMany();

		assertThat(selectedGifts, hasSize(27));
	}

	@Test
	public void selectOneGift() {

		int giftId = 1000;

		Gift gift = giftService.selectOne(giftId);

		assertThat(gift, hasProperty("giftId", equalTo(1000)));
		assertThat(gift, hasProperty("guestName", equalTo("中越典子")));
		assertThat(gift, hasProperty("giftName", equalTo("マカロン")));
		assertThat(gift, hasProperty("price", equalTo("120個入　3938円")));
		assertThat(gift, hasProperty("image", equalTo("1000.jpg")));
		assertThat(gift, hasProperty("shop", equalTo("ジャン＝ポール･エヴァン伊勢丹新宿店")));
		assertThat(gift, hasProperty("address", equalTo("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階")));
		assertThat(gift, hasProperty("phone", equalTo("03-3352-1111")));
	}
}