package com.snitch.integrationtest.domain.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.snitch.domain.model.gift.Gift;
import com.snitch.domain.service.GiftService;

@SpringBootTest
@Transactional
public class GiftServiceTestIT {

    @Autowired
    GiftService giftService;

    @Test
    public void countGiftByKeyword() {

	String keyword = "マカロン";

	int expected = 2;
	int actual = giftService.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void countGiftByKeyword_zero() {

	String keyword = "H#4kこ";

	int expected = 0;
	int actual = giftService.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void countGiftByKeyword_zero_disabledGift() {

	String keyword = "無効ギフト";

	int expected = 0;
	int actual = giftService.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void countGiftByKeyword_zero_disabledRecommender() {

	String keyword = "無効レコメンダー";

	int expected = 0;
	int actual = giftService.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void countGiftByKeyword_zero_disabledGift_disabledRecommender() {

	String keyword = "無効ギフト無効レコメンダー";

	int expected = 0;
	int actual = giftService.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void searchGift_found() {

	String keyword = "マカロン";

	List<Gift> gift = giftService.search(keyword);

	assertThat(gift, hasItems(hasProperty("giftId", is(1000))));
	assertThat(gift, hasItems(hasProperty("recommenderName", is("中越典子"))));
	assertThat(gift, hasItems(hasProperty("giftName", is("マカロン"))));
	assertThat(gift, hasItems(hasProperty("price", is("120個入　3938円"))));
	assertThat(gift, hasItems(hasProperty("image", is("1000.jpg"))));
	assertThat(gift, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
	assertThat(gift, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
	assertThat(gift, hasItems(hasProperty("phone", is("03-3352-1111"))));
    }

    @Test
    public void searchGift_notFound() {

	String keyword = "H#4kこ";

	List<Gift> gift = giftService.search(keyword);

	assertThat(gift, is(empty()));
    }

    @Test
    public void searchGift_notFound_disabledGift() {
	String keyword = "無効ギフト";

	List<Gift> giftList = giftService.search(keyword);

	assertThat(giftList, is(empty()));
    }

    @Test
    public void searchGift_notFound_disabledRecommender() {
	String keyword = "無効レコメンダー";

	List<Gift> giftList = giftService.search(keyword);

	assertThat(giftList, is(empty()));
    }

    @Test
    public void searchGift_notFound_disabledGift_disabledRecommender() {
	String keyword = "無効ギフト無効レコメンダー";

	List<Gift> giftList = giftService.search(keyword);

	assertThat(giftList, is(empty()));
    }

    @Test
    public void selectManyGifts() {

	List<Gift> gift = giftService.selectMany();

	assertThat(gift, hasSize(27));
    }

    @Test
    public void selectOneGift_success() {

	int giftId = 1000;

	Gift gift = giftService.selectOne(giftId);

	assertThat(gift, hasProperty("giftId", equalTo(1000)));
	assertThat(gift, hasProperty("recommenderName", equalTo("中越典子")));
	assertThat(gift, hasProperty("giftName", equalTo("マカロン")));
	assertThat(gift, hasProperty("price", equalTo("120個入　3938円")));
	assertThat(gift, hasProperty("image", equalTo("1000.jpg")));
	assertThat(gift, hasProperty("shop", equalTo("ジャン＝ポール･エヴァン伊勢丹新宿店")));
	assertThat(gift, hasProperty("address", equalTo("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階")));
	assertThat(gift, hasProperty("phone", equalTo("03-3352-1111")));
    }

    @Test
    public void selectOneGift_fail_giftIdDoesNotExist() {

	int giftId = 9999;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    giftService.selectOne(giftId);
	});
    }

    @Test
    public void selectOneGift_fail_disabledGift() {

	int giftId = 1031;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    giftService.selectOne(giftId);
	});
    }

    @Test
    public void selectOneGift_fail_disabledRecommender() {

	int giftId = 1032;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    giftService.selectOne(giftId);
	});
    }

    @Test
    public void selectOneGift_fail_disabledGift_disabledRecommender() {

	int giftId = 1033;

	Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
	    giftService.selectOne(giftId);
	});
    }
}