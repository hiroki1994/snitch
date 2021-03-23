package com.example.demo.unittest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.repository.jdbc.GiftDaoJdbcImpl;



@SpringBootTest
@AutoConfigureMockMvc
public class GiftDaoJdbcImplTest {

	@Autowired
	GiftDaoJdbcImpl giftDaoJdbcImpl;

	@Test
	public void countGiftByKeyword() {

		String keyword = "マカロン";

		int expected = 2;
		int actual = giftDaoJdbcImpl.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void countGiftByKeyword_zero() {

		String keyword = "H#4kこ";

		int expected = 0;
		int actual = giftDaoJdbcImpl.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void searchGift_found() {
		String keyword = "マカロン";

		List<Gift> giftList = giftDaoJdbcImpl.search(keyword);

		assertThat(giftList, hasItems(hasProperty("giftId", is(1000))));
		assertThat(giftList, hasItems(hasProperty("guestName", is("中越典子"))));
		assertThat(giftList, hasItems(hasProperty("giftName", is("マカロン"))));
		assertThat(giftList, hasItems(hasProperty("price", is("120個入　3938円"))));
		assertThat(giftList, hasItems(hasProperty("image", is("1000.jpg"))));
		assertThat(giftList, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
		assertThat(giftList, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
		assertThat(giftList, hasItems(hasProperty("phone", is("03-3352-1111"))));
	}

	@Test
	public void searchGift_notFound() {
		String keyword = "H#4kこ";

		List<Gift> giftList = giftDaoJdbcImpl.search(keyword);

		assertThat(giftList, is(empty()));
	}

	@Test
	public void listGifts() {

		List<Gift> giftList = giftDaoJdbcImpl.selectMany();

		assertThat(giftList, hasSize(27));
	}

	@Test
	public void selectOneGift() {

		int giftId = 1000;

		Gift gift = giftDaoJdbcImpl.selectOne(giftId);

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
