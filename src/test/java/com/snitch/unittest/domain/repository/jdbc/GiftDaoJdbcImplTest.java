package com.snitch.unittest.domain.repository.jdbc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.snitch.domain.model.gift.Gift;
import com.snitch.domain.repository.jdbc.GiftDaoJdbcImpl;

@SpringBootTest
public class GiftDaoJdbcImplTest {

    @Autowired
    GiftDaoJdbcImpl giftDaoJdbcImpl;

    @Test
    public void countGiftByKeyword() {

	String keyword = "マカロン";

	int expected = 2;
	int actual = giftDaoJdbcImpl.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void countGiftByKeyword_zero() {

	String keyword = "H#4kこ";

	int expected = 0;
	int actual = giftDaoJdbcImpl.count(keyword);

	assertEquals(expected, actual);
    }

    @Test
    public void searchGift_found() {

	String keyword = "マカロン";

	List<Gift> gift = giftDaoJdbcImpl.search(keyword);

	assertThat(gift, hasItems(hasProperty("giftId", is(1000))));
	assertThat(gift, hasItems(hasProperty("guestName", is("中越典子"))));
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

	List<Gift> gift = giftDaoJdbcImpl.search(keyword);

	assertThat(gift, is(empty()));
    }

    @Test
    public void selectManyGifts() {

	List<Gift> gift = giftDaoJdbcImpl.selectMany();

	assertThat(gift, hasSize(27));
    }

    @Test
    public void selectOneGift_success() {

	int giftId = 1000;

	Gift gift = giftDaoJdbcImpl.selectOne(giftId);

	assertThat(gift, hasProperty("giftId", equalTo(1000)));
	assertThat(gift, hasProperty("guestName", equalTo("中越典子")));
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
	    giftDaoJdbcImpl.selectOne(giftId);
	});
    }
}