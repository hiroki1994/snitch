package com.example.demo.unittest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.jdbc.FavGiftDaoJdbcImpl;



@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Sql({"/test_schema.sql", "/test_data.sql"})
public class FavGiftDaoJdbcTest {

	@Autowired
	FavGiftDaoJdbcImpl favGiftDaoJdbcImpl;

	@Test
	public void countFavGift() throws Exception {
		String userName = "userName3";

		int expected = 2;
		int actual = favGiftDaoJdbcImpl.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void listFavGift() throws Exception {
		String userName = "userName3";

		List<FavGift> allFavGifts = favGiftDaoJdbcImpl.selectAll(userName);

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

	@Test
	public void createFavGift()throws Exception {

		String userName = "userName3";

		int giftId = 1002;

		int expected = 1;
		int actual = favGiftDaoJdbcImpl.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift()throws Exception {

		String userName = "userName3";

		int giftId = 1000;

		int expected = 1;
		int actual = favGiftDaoJdbcImpl.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_found()throws Exception {

		String userName = "userName3";

		int giftId = 1001;

		int expected = 2;
		int actual = favGiftDaoJdbcImpl.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}
}
