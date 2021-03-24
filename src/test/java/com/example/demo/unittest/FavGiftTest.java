package com.example.demo.unittest;


import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.FavGift;

@SpringBootTest
public class FavGiftTest {

	@Test
	public void setGetFavGift() throws Exception {

		FavGift favGift = new FavGift();

		favGift.setFavId((int)2000);
		favGift.setUserId((int)10);
		favGift.setGiftId((int)2000);
		favGift.setGuestName("testGuest");
		favGift.setGiftName("testGift");
		favGift.setPrice("testPrice");
		favGift.setImage("2000.jpg");
		favGift.setDescription("testDescription");
		favGift.setShop("testShop");
		favGift.setAddress("testAddress");
		favGift.setPhone("testPhone");
		favGift.setUnavailableFlag(0);

		int actualFavId = favGift.getFavId();
		int actualUserId = favGift.getUserId();
		int actualGiftId = favGift.getGiftId();
		String actualGuestName = favGift.getGuestName();
		String actualGiftName = favGift.getGiftName();
		String actualPrice = favGift.getPrice();
		String actualImage = favGift.getImage();
		String actualDescription = favGift.getDescription();
		String actualShop = favGift.getShop();
		String actualAddress = favGift.getAddress();
		String actualPhone = favGift.getPhone();
		int actualFlag = favGift.getUnavailableFlag();

		assertEquals(2000, actualFavId);
		assertEquals(10, actualUserId);
		assertEquals(2000, actualGiftId);
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
	public void favGift_empty() throws Exception {

		FavGift favGift = new FavGift();

		int actualFavId = favGift.getFavId();
		int actualUserId = favGift.getUserId();
		int actualGiftId = favGift.getGiftId();
		String actualGuestName = favGift.getGuestName();
		String actualGiftName = favGift.getGiftName();
		String actualPrice = favGift.getPrice();
		String actualImage = favGift.getImage();
		String actualDescription = favGift.getDescription();
		String actualShop = favGift.getShop();
		String actualAddress = favGift.getAddress();
		String actualPhone = favGift.getPhone();
		int actualFlag = favGift.getUnavailableFlag();

		assertEquals(0, actualFavId);
		assertEquals(0, actualUserId);
		assertEquals(0, actualGiftId);
		assertEquals(null, actualGuestName);
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
