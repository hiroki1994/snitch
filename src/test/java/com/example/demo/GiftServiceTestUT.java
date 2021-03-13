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

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.repository.GiftDao;
import com.example.demo.login.domain.service.GiftService;



@SpringBootTest
@AutoConfigureMockMvc
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
	public void お土産件数取得成功() {

		String keyword = "マカロン";

		when(giftDao.count(keyword)).thenReturn(2);

		int expected = 2;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);

	}

	@Test
	public void お土産件数取得失敗() {

		String keyword = "H#4kこ";

		when(giftDao.count(keyword)).thenReturn(0);

		int expected = 0;
		int actual = giftService.count(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void お土産検索() {

		String keyword = "マカロン";

		List<Gift> selectedGifts = new ArrayList<>();

		when(giftDao.search(keyword)).thenReturn(selectedGifts);

		List<Gift> expected = selectedGifts;
		List<Gift> actual = giftService.search(keyword);

		assertEquals(expected, actual);
	}

	@Test
	public void お土産一覧() {

		List<Gift> selectedGifts = new ArrayList<>();

		when(giftDao.selectMany()).thenReturn(selectedGifts);

		List<Gift> expected = selectedGifts;
		List<Gift> actual = giftService.selectMany();

		assertEquals(expected, actual);
	}

	@Test
	public void お土産1件取得() {

		int giftId = 1000;

		Gift gift = new Gift();

		when(giftDao.selectOne(giftId)).thenReturn(gift);

		Gift expected = gift;
		Gift actual = giftService.selectOne(giftId);

		assertEquals(expected, actual);
	}
}