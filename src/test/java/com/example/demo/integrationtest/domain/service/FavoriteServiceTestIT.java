package com.example.demo.integrationtest.domain.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.favorite.FavGift;
import com.example.demo.domain.service.FavoriteService;



@SpringBootTest
@Transactional
public class FavoriteServiceTestIT {

	@Autowired
	FavoriteService favGiftService;


	@Test
	public void createOneFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1004;

		int expected = 1;
		int actual = favGiftService.createOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void createOneFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			favGiftService.createOne(userName, giftId);
		});
	}

	@Test
	public void createOneFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.createOne(userName, giftId);
		});
	}

	@Test
	public void createOneFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.createOne(userName, giftId);
		});
	}

	@Test
	public void deleteOneFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1001;

		int expected = 1;
		int actual = favGiftService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		int expected = 0;
		int actual = favGiftService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		int expected = 0;
		int actual = favGiftService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.deleteOne(userName, giftId);
		});
	}

	@Test
	public void deleteOneFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.deleteOne(userName, giftId);
		});
	}

	@Test
	public void countFavGift_success() throws Exception {

		String userName = "userName3";

		int expected = 2;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_success_noFavGift() throws Exception {

		String userName = "userName4";

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.count(userName);
		});
	}

	@Test
	public void searchFavId_success_found() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		boolean expected = true;
		boolean actual = favGiftService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_success_notFound() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		boolean expected = false;
		boolean actual = favGiftService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1002;

		boolean expected = false;
		boolean actual = favGiftService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		boolean expected = false;
		boolean actual = favGiftService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void getFavGiftList_success() throws Exception {

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

	@Test
	public void getFavGiftList_success_noFavGift() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = favGiftService.selectAll(userName);

		assertThat(allFavGifts, is(empty()));
	}

	@Test
	public void getFavGiftList_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.selectAll(userName);
		});
	}

	@Test
	public void deleteAllFavGift_success() throws Exception {

		String userName = "userName3";

		int expected = 2;
		int actual = favGiftService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_success_addNoFav() throws Exception {

		String userName = "userName4";

		int expected = 0;
		int actual = favGiftService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftService.deleteMany(userName);
		});
	}
}