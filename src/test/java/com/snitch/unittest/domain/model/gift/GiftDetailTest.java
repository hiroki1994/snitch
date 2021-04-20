package com.snitch.unittest.domain.model.gift;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.gift.GiftDetail;

@SpringBootTest
public class GiftDetailTest {

    @Test
    public void setGetGiftDetail() throws Exception {

	GiftDetail giftDetail = new GiftDetail();
	giftDetail.setGiftId((int) 2000);
	giftDetail.setRecommenderName("testRecommender");
	giftDetail.setGiftName("testgift");
	giftDetail.setPrice("testPrice");
	giftDetail.setImage("2000.jpg");
	giftDetail.setDescription("testDescription");
	giftDetail.setShop("testShop");
	giftDetail.setAddress("testAddress");
	giftDetail.setPhone("testPhone");

	int actualGiftId = giftDetail.getGiftId();
	String actualRecommenderName = giftDetail.getRecommenderName();
	String actualGiftName = giftDetail.getGiftName();
	String actualPrice = giftDetail.getPrice();
	String actualImage = giftDetail.getImage();
	String actualDescription = giftDetail.getDescription();
	String actualShop = giftDetail.getShop();
	String actualAddress = giftDetail.getAddress();
	String actualPhone = giftDetail.getPhone();

	assertEquals(2000, actualGiftId);
	assertEquals("testRecommender", actualRecommenderName);
	assertEquals("testgift", actualGiftName);
	assertEquals("testPrice", actualPrice);
	assertEquals("2000.jpg", actualImage);
	assertEquals("testDescription", actualDescription);
	assertEquals("testShop", actualShop);
	assertEquals("testAddress", actualAddress);
	assertEquals("testPhone", actualPhone);
    }

    @Test
    public void getGiftDetail_empty() throws Exception {

	GiftDetail giftDetail = new GiftDetail();
	int actualGiftId = giftDetail.getGiftId();
	String actualRecommenderName = giftDetail.getRecommenderName();
	String actualGiftName = giftDetail.getGiftName();
	String actualPrice = giftDetail.getPrice();
	String actualImage = giftDetail.getImage();
	String actualDescription = giftDetail.getDescription();
	String actualShop = giftDetail.getShop();
	String actualAddress = giftDetail.getAddress();
	String actualPhone = giftDetail.getPhone();

	assertEquals(0, actualGiftId);
	assertEquals(null, actualRecommenderName);
	assertEquals(null, actualGiftName);
	assertEquals(null, actualPrice);
	assertEquals(null, actualImage);
	assertEquals(null, actualDescription);
	assertEquals(null, actualShop);
	assertEquals(null, actualAddress);
	assertEquals(null, actualPhone);
    }
}