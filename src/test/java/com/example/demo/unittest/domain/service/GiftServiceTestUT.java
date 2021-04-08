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
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.repository.GiftDao;
import com.example.demo.domain.service.GiftService;



@SpringBootTest
public class GiftServiceTestUT {

	@Mock
	GiftDao giftDao;

	@InjectMocks
	GiftService giftService;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void countGiftByKeyword() {

		String keyword = "マカロン";

		when(giftDao.count(keyword)).thenReturn(2);

		int expected = 2;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);

	}

	@Test
	public void countGiftByKeyword_zero() {

		String keyword = "H#4kこ";

		when(giftDao.count(keyword)).thenReturn(0);

		int expected = 0;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void searchGift() {

		String keyword = "マカロン";

		List<Gift> selectedGifts = new ArrayList<>();

		when(giftDao.search(keyword)).thenReturn(selectedGifts);

		List<Gift> expected = selectedGifts;
		List<Gift> actual = giftService.search(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void listGifts() {

		List<Gift> selectedGifts = new ArrayList<>();

		when(giftDao.selectMany()).thenReturn(selectedGifts);

		List<Gift> expected = selectedGifts;
		List<Gift> actual = giftService.selectMany();

		assertEquals(expected, actual);
	}

	@Test
	public void selectOneGift_success() {

		int giftId = 1000;

		Gift gift = new Gift();

		when(giftDao.selectOne(giftId)).thenReturn(gift);

		Gift expected = gift;
		Gift actual = giftService.selectOne(giftId);

		assertEquals(expected, actual);
	}

	@Test
	public void selectOneGift_fail_giftIdDoesNotExist() {

		int giftId = 9999;

		when(giftDao.selectOne(giftId)).thenThrow(EmptyResultDataAccessException.class);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			giftService.selectOne(giftId);
		});
	}
}