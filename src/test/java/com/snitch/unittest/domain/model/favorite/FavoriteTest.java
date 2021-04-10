package com.snitch.unittest.domain.model.favorite;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.favorite.Favorite;

@SpringBootTest
public class FavoriteTest {

    @Test
    public void setGetFavorite() throws Exception {

	Favorite favorite = new Favorite();
	favorite.setFavoriteId((int) 2000);
	favorite.setUserId((int) 10);
	favorite.setGiftId((int) 2000);
	favorite.setGuestName("testGuest");
	favorite.setGiftName("testGift");
	favorite.setPrice("testPrice");
	favorite.setImage("2000.jpg");
	favorite.setDescription("testDescription");
	favorite.setShop("testShop");
	favorite.setAddress("testAddress");
	favorite.setPhone("testPhone");
	favorite.setUnavailableFlag(0);

	int actualFavoriteId = favorite.getFavoriteId();
	int actualUserId = favorite.getUserId();
	int actualGiftId = favorite.getGiftId();
	String actualGuestName = favorite.getGuestName();
	String actualGiftName = favorite.getGiftName();
	String actualPrice = favorite.getPrice();
	String actualImage = favorite.getImage();
	String actualDescription = favorite.getDescription();
	String actualShop = favorite.getShop();
	String actualAddress = favorite.getAddress();
	String actualPhone = favorite.getPhone();
	int actualFlag = favorite.getUnavailableFlag();

	assertEquals(2000, actualFavoriteId);
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
    public void getFavorite_empty() throws Exception {

	Favorite favorite = new Favorite();
	int actualFavoriteId = favorite.getFavoriteId();
	int actualUserId = favorite.getUserId();
	int actualGiftId = favorite.getGiftId();
	String actualGuestName = favorite.getGuestName();
	String actualGiftName = favorite.getGiftName();
	String actualPrice = favorite.getPrice();
	String actualImage = favorite.getImage();
	String actualDescription = favorite.getDescription();
	String actualShop = favorite.getShop();
	String actualAddress = favorite.getAddress();
	String actualPhone = favorite.getPhone();
	int actualFlag = favorite.getUnavailableFlag();

	assertEquals(0, actualFavoriteId);
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