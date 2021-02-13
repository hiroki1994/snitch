package com.example.demo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
//@Sql({"/Delete.sql"})
@ActiveProfiles("test")
public class FavGiftDaoTest {


	@Autowired
	@Qualifier("favGiftDaoJdbcImpl")
	FavGiftDao favGiftDao;

	@Test
	@Sql({"/Delete.sql", "/Insert.sql"})
	public void お気に入り件数() throws Exception {
		String userName = "userName3";

		assertEquals(favGiftDao.count(userName), 1);
	}

	@Test
	@Sql({"/Insert.sql"})
	public void お気に入り一覧() throws Exception {
		String userName = "userName3";

		List<FavGift> allFavGifts = favGiftDao.selectAll(userName);
		assertThat(allFavGifts, hasItems(hasProperty("favId", is(0))));
		assertThat(allFavGifts, hasItems(hasProperty("userId", is(2))));
		assertThat(allFavGifts, hasItems(hasProperty("giftId", is(1066))));
		assertThat(allFavGifts, hasItems(hasProperty("guestName", is("中越典子"))));
		assertThat(allFavGifts, hasItems(hasProperty("giftName", is("マカロン"))));
		assertThat(allFavGifts, hasItems(hasProperty("price", is("120個入　3938円"))));
		assertThat(allFavGifts, hasItems(hasProperty("image", is("1066.jpg"))));
		assertThat(allFavGifts, hasItems(hasProperty("shop", is("ジャン＝ポール･エヴァン伊勢丹新宿店"))));
		assertThat(allFavGifts, hasItems(hasProperty("address", is("東京都新宿区新宿3-14-1伊勢丹新宿店本館B1階"))));
		assertThat(allFavGifts, hasItems(hasProperty("phone", is("03-3352-1111"))));
	}
}
