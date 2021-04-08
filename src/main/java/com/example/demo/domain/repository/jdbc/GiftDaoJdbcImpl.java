package com.example.demo.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.repository.GiftDao;

@Repository
public class GiftDaoJdbcImpl implements GiftDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int count(String keyword) throws DataAccessException {

	return jdbc.queryForObject("SELECT COUNT(giftId) FROM gift INNER JOIN guest ON gift.guestId = guest.guestId "
		+ "WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag IS NULL",
		Integer.class, keyword);
    }

    @Override
    public List<Gift> search(String keyword) throws DataAccessException {

	List<Map<String, Object>> gifts = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId "
		+ "WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag IS NULL",keyword);

	List<Gift> selectedGifts = new ArrayList<>();

	for (Map<String, Object> map : gifts) {

	    Gift gift = new Gift();
	    gift.setGiftId((int) map.get("giftId"));
	    gift.setGuestName((String) map.get("guestName"));
	    gift.setGiftName((String) map.get("giftName"));
	    gift.setPrice((String) map.get("price"));
	    gift.setImage((String) map.get("image"));
	    gift.setDescription((String) map.get("description"));
	    gift.setShop((String) map.get("shop"));
	    gift.setAddress((String) map.get("address"));
	    gift.setPhone((String) map.get("phone"));
	    selectedGifts.add(gift);
	}
	return selectedGifts;
    }

    @Override
    public List<Gift> selectMany() throws DataAccessException {

	List<Map<String, Object>> gifts = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId "
		+ "WHERE gift.unavailableFlag IS NULL ORDER BY RANDOM() LIMIT 27");

	List<Gift> selectedGifts = new ArrayList<>();

	for (Map<String, Object> map : gifts) {

	    Gift gift = new Gift();
	    gift.setGiftId((int) map.get("giftId"));
	    gift.setGuestName((String) map.get("guestName"));
	    gift.setGiftName((String) map.get("giftName"));
	    gift.setPrice((String) map.get("price"));
	    gift.setImage((String) map.get("image"));
	    gift.setDescription((String) map.get("description"));
	    gift.setShop((String) map.get("shop"));
	    gift.setAddress((String) map.get("address"));
	    gift.setPhone((String) map.get("phone"));
	    selectedGifts.add(gift);
	}
	return selectedGifts;
    }

    @Override
    public Gift selectOne(int giftId) throws EmptyResultDataAccessException {

	Map<String, Object> singleGift = jdbc.queryForMap("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId "
		+ "WHERE giftId = ? AND gift.unavailableFlag IS NULL",giftId);

	Gift gift = new Gift();
	gift.setGiftId((int) singleGift.get("giftId"));
	gift.setGuestName((String) singleGift.get("guestName"));
	gift.setGiftName((String) singleGift.get("giftName"));
	gift.setPrice((String) singleGift.get("price"));
	gift.setImage((String) singleGift.get("image"));
	gift.setDescription((String) singleGift.get("description"));
	gift.setShop((String) singleGift.get("shop"));
	gift.setAddress((String) singleGift.get("address"));
	gift.setPhone((String) singleGift.get("phone"));

	return gift;
    }
}