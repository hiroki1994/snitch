package com.snitch.unittest.domain.model.gift;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.gift.Gift;

@SpringBootTest
public class GiftTest {

    @Test
    public void setGetGift() throws Exception {

	Gift gift = new Gift();
	gift.setGiftId((int) 2000);
	gift.setRecommenderId((int) 2000);
	gift.setRecommenderName("testRecommender");
	gift.setGiftName("testGift");
	gift.setPrice("testPrice");
	gift.setImage("2000.jpg");
	gift.setDescription("testDescription");
	gift.setShop("testShop");
	gift.setAddress("testAddress");
	gift.setPhone("testPhone");
	gift.setEnabled(true);

	int actualGiftId = gift.getGiftId();
	int actualRecommenderId = gift.getRecommenderId();
	String actualRecommenderName = gift.getRecommenderName();
	String actualGiftName = gift.getGiftName();
	String actualPrice = gift.getPrice();
	String actualImage = gift.getImage();
	String actualDescription = gift.getDescription();
	String actualShop = gift.getShop();
	String actualAddress = gift.getAddress();
	String actualPhone = gift.getPhone();
	boolean actualFlag = gift.isEnabled();

	assertEquals(2000, actualGiftId);
	assertEquals(2000, actualRecommenderId);
	assertEquals("testRecommender", actualRecommenderName);
	assertEquals("testGift", actualGiftName);
	assertEquals("testPrice", actualPrice);
	assertEquals("2000.jpg", actualImage);
	assertEquals("testDescription", actualDescription);
	assertEquals("testShop", actualShop);
	assertEquals("testAddress", actualAddress);
	assertEquals("testPhone", actualPhone);
	assertEquals(true, actualFlag);
    }

    @Test
    public void getGift_empty() throws Exception {

	Gift gift = new Gift();
	int actualGiftId = gift.getGiftId();
	int actualRecommenderId = gift.getRecommenderId();
	String actualRecommenderName = gift.getRecommenderName();
	String actualGiftName = gift.getGiftName();
	String actualPrice = gift.getPrice();
	String actualImage = gift.getImage();
	String actualDescription = gift.getDescription();
	String actualShop = gift.getShop();
	String actualAddress = gift.getAddress();
	String actualPhone = gift.getPhone();
	boolean actualFlag = gift.isEnabled();

	assertEquals(0, actualGiftId);
	assertEquals(0, actualRecommenderId);
	assertEquals(null, actualRecommenderName);
	assertEquals(null, actualGiftName);
	assertEquals(null, actualPrice);
	assertEquals(null, actualImage);
	assertEquals(null, actualDescription);
	assertEquals(null, actualShop);
	assertEquals(null, actualAddress);
	assertEquals(null, actualPhone);
	assertEquals(false, actualFlag);
    }
}