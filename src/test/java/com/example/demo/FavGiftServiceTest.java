package com.example.demo;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.login.domain.service.FavGiftService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
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
		int giftId = 100;
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


}
