package com.example.demo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;



@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Sql({"/Delete.sql","/Schema.sql", "/Insert.sql"})
public class FavGiftDaoTest {


	@Autowired
	@Qualifier("favGiftDaoJdbcImpl")
	FavGiftDao favGiftDao;

	@Test
	public void お気に入り件数() throws Exception {
		String userName = "userName3";

		assertEquals(favGiftDao.count(userName), 2);
	}

	@Test
	public void お気に入り一覧() throws Exception {
		String userName = "userName3";

		List<FavGift> allFavGifts = favGiftDao.selectAll(userName);
		assertThat(allFavGifts, hasItems(hasProperty("favId", is(0))));
		assertThat(allFavGifts, hasItems(hasProperty("userId", is(1))));
		assertThat(allFavGifts, hasItems(hasProperty("giftId", is(1000))));
		assertThat(allFavGifts, hasItems(hasProperty("guestName", is("中越典子"))));
		assertThat(allFavGifts, hasItems(hasProperty("giftName", is("マカロン"))));
		assertThat(allFavGifts, hasItems(hasProperty("price", is("120個入　3938円"))));
		assertThat(allFavGifts, hasItems(hasProperty("image", is("1000.jpg"))));
		assertThat(allFavGifts, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
		assertThat(allFavGifts, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
		assertThat(allFavGifts, hasItems(hasProperty("phone", is("03-3352-1111"))));
	}

	@Test
	public void お気に入り登録()throws Exception {

		String userName = "userName3";

		int giftId = 1001;

		assertEquals(favGiftDao.create(userName, giftId), 1);
	}

	@Test
	public void お気に入り削除()throws Exception {

		String userName = "userName3";

		int giftId = 1000;

		assertEquals(favGiftDao.delete(userName, giftId), 1);
	}

	@Test
	public void お気に入りID取得()throws Exception {

		String userName = "userName3";

		int giftId = 1001;

		assertEquals(favGiftDao.searchFavId(userName, giftId), 2);
	}
}
