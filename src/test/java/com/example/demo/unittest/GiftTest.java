package com.example.demo.unittest;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.Gift;

@SpringBootTest
public class GiftTest {

	@Test
	public void setGetGift() throws Exception {

		Gift gift = new Gift();

		gift.setGiftId((int)2000);
		gift.setGuestId((int)2000);
		gift.setGuestName("testGuest");
		gift.setGiftName("testGift");
		gift.setPrice("testPrice");
		gift.setImage("2000.jpg");
		gift.setDescription("testDescription");
		gift.setShop("testShop");
		gift.setAddress("testAddress");
		gift.setPhone("testPhone");
		gift.setUnavailableFlag(0);

		int actualGiftId = gift.getGiftId();
		int actualGuestId = gift.getGuestId();
		String actualGuestName = gift.getGuestName();
		String actualGiftName = gift.getGiftName();
		String actualPrice = gift.getPrice();
		String actualImage = gift.getImage();
		String actualDescription = gift.getDescription();
		String actualShop = gift.getShop();
		String actualAddress = gift.getAddress();
		String actualPhone = gift.getPhone();
		int actualFlag = gift.getUnavailableFlag();

		assertEquals(2000, actualGiftId);
		assertEquals(2000, actualGuestId);
		assertEquals("testGuest", actualGuestName);
		assertEquals("testGift", actualGiftName);
		assertEquals("testPrice", actualPrice);
		assertEquals("2000.jpg", actualImage);
		assertEquals("testDescription", actualDescription);
		assertEquals("testShop", actualShop);
		assertEquals("testAddress", actualAddress);
		assertEquals("testPhone", actualPhone);
		assertEquals(0, actualFlag);
	}

	@Test
	public void Gift_empty() throws Exception {

		Gift gift = new Gift();

		int actualGiftId = gift.getGiftId();
		int actualGuestId = gift.getGuestId();
		String actualGuestName = gift.getGuestName();
		String actualGiftName = gift.getGiftName();
		String actualPrice = gift.getPrice();
		String actualImage = gift.getImage();
		String actualDescription = gift.getDescription();
		String actualShop = gift.getShop();
		String actualAddress = gift.getAddress();
		String actualPhone = gift.getPhone();
		int actualFlag = gift.getUnavailableFlag();

		assertEquals(0, actualGiftId);
		assertEquals(0, actualGuestId);
		assertEquals(null, actualGuestName);
		assertEquals(null, actualGiftName);
		assertEquals(null, actualPrice);
		assertEquals(null, actualImage);
		assertEquals(null, actualDescription);
		assertEquals(null, actualShop);
		assertEquals(null, actualAddress);
		assertEquals(null, actualPhone);
		assertEquals(0, actualFlag);
	}
}
