package com.snitch.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.snitch.domain.model.gift.Gift;
import com.snitch.domain.repository.GiftDao;

@Repository
public class GiftDaoJdbcImpl implements GiftDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int count(String keyword) throws DataAccessException {

	return jdbc.queryForObject("SELECT COUNT(giftId) "
					+ "FROM gifts "
					+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
					+ "WHERE CONCAT(giftName, description, shop, address, recommenderName) "
					+ "LIKE '%'||?||'%' "
					+ "AND gifts.isEnabled IS true "
					+ "AND recommenders.isEnabled IS true"
					, Integer.class, keyword);
    }

    @Override
    public List<Gift> search(String keyword) throws DataAccessException {

	List<Map<String, Object>> gifts = jdbc.queryForList("SELECT * "
								+ "FROM gifts "
								+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
								+ "WHERE CONCAT(giftName, description, shop, address, recommenderName) "
								+ "LIKE '%'||?||'%' "
								+ "AND gifts.isEnabled IS true "
								+ "AND recommenders.isEnabled IS true"
								, keyword);

	List<Gift> selectedGifts = new ArrayList<>();

	for (Map<String, Object> map : gifts) {

	    Gift gift = new Gift();
	    gift.setGiftId((int) map.get("giftId"));
	    gift.setRecommenderName((String) map.get("recommenderName"));
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

	List<Map<String, Object>> gifts = jdbc.queryForList("SELECT * "
								+ "FROM gifts "
								+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
								+ "WHERE gifts.isEnabled IS true "
								+ "AND recommenders.isEnabled IS true "
								+ "ORDER BY RANDOM() "
								+ "LIMIT 27");

	List<Gift> selectedGifts = new ArrayList<>();

	for (Map<String, Object> map : gifts) {

	    Gift gift = new Gift();
	    gift.setGiftId((int) map.get("giftId"));
	    gift.setRecommenderName((String) map.get("recommenderName"));
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

	Map<String, Object> singleGift = jdbc.queryForMap("SELECT * "
							+ "FROM gifts "
							+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
							+ "WHERE giftId = ? "
							+ "AND gifts.isEnabled IS true "
							+ "AND recommenders.isEnabled IS true"
							, giftId);

	Gift gift = new Gift();
	gift.setGiftId((int) singleGift.get("giftId"));
	gift.setRecommenderName((String) singleGift.get("recommenderName"));
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