package com.example.demo.unittest.domain.model.gift;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.model.gift.GiftDetail;

@SpringBootTest
public class GiftDetailTest {

	@Test
	public void setGetGiftDetail() throws Exception {

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
	public void GiftDetail_empty() throws Exception {

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