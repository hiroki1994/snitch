package com.example.demo.unittest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;



@SpringBootTest
@Transactional
public class FavGiftDaoTest {

	@Autowired
	@Qualifier("favGiftDaoJdbcImpl")
	FavGiftDao favGiftDao;

	@Test
	public void countFavGift_success() throws Exception {
		String userName = "userName3";

		int expected = 2;
		int actual = favGiftDao.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_success_noFavGift() throws Exception {

		String userName = "userName4";

		int expected = 0;
		int actual = favGiftDao.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.count(userName);
		});
	}

	@Test
	public void listFavGift_success() throws Exception {
		String userName = "userName3";

		List<FavGift> allFavGifts = favGiftDao.selectAll(userName);

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
	public void listFavGift_success_noFavGift() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = favGiftDao.selectAll(userName);

		assertThat(allFavGifts, is(empty()));
	}

	@Test
	public void listFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.selectAll(userName);
		});
	}

	@Test
	public void createFavGift_success()throws Exception {

		String userName = "userName3";

		int giftId = 1004;

		int expected = 1;
		int actual = favGiftDao.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void createFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			favGiftDao.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.create(userName, giftId);
		});
	}

	@Test
	public void createFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.create(userName, giftId);
		});
	}

	@Test
	public void deleteFavGift_success()throws Exception {

		String userName = "userName3";

		int giftId = 1000;

		int expected = 1;
		int actual = favGiftDao.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		int expected = 0;
		int actual = favGiftDao.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		int expected = 0;
		int actual = favGiftDao.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.delete(userName, giftId);
		});
	}

	@Test
	public void deleteFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.delete(userName, giftId);
		});
	}

	@Test
	public void searchFavId_success_found()throws Exception {

		String userName = "userName3";

		int giftId = 1001;

		int expected = 2;
		int actual = favGiftDao.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_success_notFound() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.existFavId(userName, giftId);
		});
	}

	@Test
	public void searchFavId_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1002;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.existFavId(userName, giftId);
		});
	}

	@Test
	public void searchFavId_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.existFavId(userName, giftId);
		});
	}

	@Test
	public void deleteAllFavGift_success() throws Exception {

		String userName = "userName3";

		int expected = 2;
		int actual = favGiftDao.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_success_addNoFav() throws Exception {

		String userName = "userName4";

		int expected = 0;
		int actual = favGiftDao.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favGiftDao.deleteMany(userName);
		});
	}
}