package com.example.demo;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;
import com.example.demo.login.domain.service.FavGiftService;



@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class FavGiftServiceTestUT {


	@Mock
	FavGiftDao favGiftDao;

	@InjectMocks
	FavGiftService favGiftService;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void お気に入り登録成功() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.create(userName, giftId)).thenReturn(1);

		boolean expected = true;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録giftId_お気に入り登録失敗() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.create(userName, giftId)).thenThrow(DataIntegrityViolationException.class);

		boolean expected = false;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録userName_お気に入り登録失敗() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録userName_未登録giftId_お気に入り登録失敗() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.create(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.create(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 登録済お気に入り削除成功() throws Exception {

		String userName = "userName3";
		int giftId = 1001;

		when(favGiftDao.delete(userName, giftId)).thenReturn(1);

		boolean expected = true;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未追加お気に入り削除失敗() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.delete(userName, giftId)).thenReturn(0);

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録giftId_お気に入り削除失敗() throws Exception {

		String userName = "userName3";
		int giftId = 9999;

		when(favGiftDao.delete(userName, giftId)).thenReturn(0);

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録userName_お気に入り削除失敗() throws Exception {

		String userName = "userName5";
		int giftId = 1000;

		when(favGiftDao.delete(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void 未登録userName_未登録giftId_お気に入り削除失敗() throws Exception {

		String userName = "userName5";
		int giftId = 9999;

		when(favGiftDao.delete(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.delete(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り件数() throws Exception {

		String userName = "userName3";

		when(favGiftDao.count(userName)).thenReturn(2);

		int expected = 2;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り未登録_お気に入り件数() throws Exception {

		String userName = "userName4";

		when(favGiftDao.count(userName)).thenReturn(0);

		int expected = 0;
		int actual = favGiftService.count(userName);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId発行済み() throws Exception {

		String userName = "userName3";
		int giftId = 1000;

		when(favGiftDao.existFavId(userName, giftId)).thenReturn(1);

		boolean expected = true;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入りId未発行() throws Exception {

		String userName = "userName3";
		int giftId = 1002;

		when(favGiftDao.existFavId(userName, giftId)).thenThrow(EmptyResultDataAccessException.class);

		boolean expected = false;
		boolean actual = favGiftService.existFavId(userName, giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void お気に入り一覧() throws Exception {

		String userName = "userName3";
		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftDao.selectAll(userName)).thenReturn(allFavGifts);

		List<FavGift> expected = allFavGifts;
		List<FavGift> actual = favGiftService.selectAll(userName);

		assertEquals(expected, actual);
	}
}
