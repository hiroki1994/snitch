package com.example.demo.unittest.domain.service;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.favorite.FavGift;
import com.example.demo.domain.repository.FavoriteDao;
import com.example.demo.domain.service.FavoriteService;



@SpringBootTest
@Transactional
public class FavoriteServiceTestUT {


	@Mock
	FavoriteDao favGiftDao;

	@InjectMocks
	FavoriteService favoriteService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void createOneFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.createOne(userName, giftId)).thenReturn(1);

		int expected = 1;
		int actual = favoriteService.createOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void createOneFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.createOne(userName, giftId)).thenThrow(DataIntegrityViolationException.class);

		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			favoriteService.createOne(userName, giftId);
		});
	}

	@Test
	public void createOneFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.createOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.createOne(userName, giftId);
		});
	}

	@Test
	public void createOneFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.createOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.createOne(userName, giftId);
		});
	}

	@Test
	public void deleteOneFavGift_success() throws Exception {

		String userName = "userName3";
		int giftId = 1001;

		when(favGiftDao.deleteOne(userName, giftId)).thenReturn(1);

		int expected = 1;
		int actual = favoriteService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.deleteOne(userName, giftId)).thenReturn(0);

		int expected = 0;
		int actual = favoriteService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_giftIdDoesNotExist() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.deleteOne(userName, giftId)).thenReturn(0);

		int expected = 0;
		int actual = favoriteService.deleteOne(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteOneFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.deleteOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.deleteOne(userName, giftId);
		});
	}

	@Test
	public void deleteOneFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.deleteOne(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.deleteOne(userName, giftId);
		});
	}

	@Test
	public void countFavGift_success() throws Exception {

		String userName = "userName3";

		when(favGiftDao.count(userName)).thenReturn(2);

		int expected = 2;
		int actual = favoriteService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_success_NoFavGift() throws Exception {

		String userName = "userName4";

		when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favoriteService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void countFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.count(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.count(userName);
		});
	}

	@Test
	public void searchFavId_success_found() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.exist(userName, giftId)).thenReturn(1);

		boolean expected = true;
		boolean actual = favoriteService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_success_notFound() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favoriteService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 1002;

		when(favGiftDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favoriteService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void searchFavId_fail_userNameAndUserIdDoesNotExist() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.exist(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favoriteService.exist(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void selectAllFavGift_success() throws Exception {

		String userName = "userName3";
		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favoriteService.selectAll(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void selectAllFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.selectAll(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.selectAll(userName);
		});
	}

	@Test
	public void deleteAllFavGift_success() throws Exception {

		String userName = "userName3";

		when(favGiftDao.deleteMany(userName)).thenReturn(2);

		int expected = 2;
		int actual = favoriteService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_success_addNoFav() throws Exception {

		String userName = "userName4";

		when(favGiftDao.deleteMany(userName)).thenReturn(0);

		int expected = 0;
		int actual = favoriteService.deleteMany(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteAllFavGift_fail_userNameDoesNotExist() throws Exception {

		String userName = "userName5";

		when(favGiftDao.deleteMany(userName)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			favoriteService.deleteMany(userName);
		});
	}
}
