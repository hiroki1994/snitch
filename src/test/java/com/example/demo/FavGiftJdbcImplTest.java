package com.example.demo;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.jdbc.FavGiftDaoJdbcImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes= {TestConfig.class})
@Sql("/Delete.sql")
public class FavGiftJdbcImplTest {

	@Autowired
	FavGiftDaoJdbcImpl favGiftDaoJdbcImpl;

	@Autowired
	@Qualifier("jdbcTemplateForAssertion")
	JdbcTemplate jdbcTemplate;




	@Test
	@Sql({"/Delete.sql", "/Insert.sql"})
	public void お気に入り一覧() throws Exception {
		String userName = "userName";

		Map<String, Object> userId = jdbcTemplate.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		List<Map<String, Object>> favGifts = jdbcTemplate.queryForList("SELECT * FROM favGift INNER JOIN gift ON favGift.giftId = gift.giftId INNER JOIN guest ON gift.guestId = guest.guestId WHERE userId = ? AND favGift.unavailableFlag IS NULL", userId.get("userId"));

		List<FavGift> allFavGiftsTest = new ArrayList<>();

		for(Map<String, Object> map: favGifts) {

			FavGift favGift = new FavGift();

			favGift.setUserId((int)map.get("favId"));
			favGift.setUserId((int)map.get("userId"));
			favGift.setGiftId((int)map.get("giftId"));
			favGift.setGuestName((String)map.get("guestName"));
			favGift.setGiftName((String)map.get("giftName"));
			favGift.setPrice((String)map.get("price"));
			favGift.setImage((String)map.get("image"));
			favGift.setDescription((String)map.get("description"));
			favGift.setShop((String)map.get("shop"));
			favGift.setAddress((String)map.get("address"));
			favGift.setPhone((String)map.get("phone"));

			allFavGiftsTest.add(favGift);
			assertThat(allFavGiftsTest, hasItems(hasProperty("giftId", is("10"))));

		}
			List<FavGift> allFavGifts = favGiftDaoJdbcImpl.selectAll(userName);
			assertThat(allFavGifts, hasItems(hasProperty("giftId", is(10))));

	}

}
