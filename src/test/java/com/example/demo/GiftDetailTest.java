package com.example.demo;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.GiftDetail;

@SpringBootTest
@AutoConfigureMockMvc
public class GiftDetailTest {

	@Test
	public void お土産詳細情報投入取得() throws Exception {

		GiftDetail giftDetail = new GiftDetail();

		giftDetail.setGiftId((int)2000);
		giftDetail.setGuestName("testGuest");
		giftDetail.setGiftName("testgift");
		giftDetail.setPrice("testPrice");
		giftDetail.setImage("2000.jpg");
		giftDetail.setDescription("testDescription");
		giftDetail.setShop("testShop");
		giftDetail.setAddress("testAddress");
		giftDetail.setPhone("testPhone");

		int actualGiftId = giftDetail.getGiftId();
		String actualGuestName = giftDetail.getGuestName();
		String actualGiftName = giftDetail.getGiftName();
		String actualPrice = giftDetail.getPrice();
		String actualImage = giftDetail.getImage();
		String actualDescription = giftDetail.getDescription();
		String actualShop = giftDetail.getShop();
		String actualAddress = giftDetail.getAddress();
		String actualPhone = giftDetail.getPhone();

		assertEquals(2000, actualGiftId);
		assertEquals("testGuest", actualGuestName);
		assertEquals("testgift", actualGiftName);
		assertEquals("testPrice", actualPrice);
		assertEquals("2000.jpg", actualImage);
		assertEquals("testDescription", actualDescription);
		assertEquals("testShop", actualShop);
		assertEquals("testAddress", actualAddress);
		assertEquals("testPhone", actualPhone);
	}

	@Test
	public void お土産詳細情報投入取得_空() throws Exception {

		GiftDetail giftDetail = new GiftDetail();

		int actualGiftId = giftDetail.getGiftId();
		String actualGuestName = giftDetail.getGuestName();
		String actualGiftName = giftDetail.getGiftName();
		String actualPrice = giftDetail.getPrice();
		String actualImage = giftDetail.getImage();
		String actualDescription = giftDetail.getDescription();
		String actualShop = giftDetail.getShop();
		String actualAddress = giftDetail.getAddress();
		String actualPhone = giftDetail.getPhone();

		assertEquals(0, actualGiftId);
		assertEquals(null, actualGuestName);
		assertEquals(null, actualGiftName);
		assertEquals(null, actualPrice);
		assertEquals(null, actualImage);
		assertEquals(null, actualDescription);
		assertEquals(null, actualShop);
		assertEquals(null, actualAddress);
		assertEquals(null, actualPhone);
	}
}
