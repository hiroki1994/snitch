package com.example.demo.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.favorite.FavGift;
import com.example.demo.domain.repository.FavGiftDao;

@Repository
public class FavGiftDaoJdbcImpl implements FavGiftDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<FavGift> selectAll(String userName) throws EmptyResultDataAccessException {

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users "
							+ "WHERE userName = ? "
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		List<Map<String, Object>> favGifts = jdbc.queryForList("SELECT * "
										+ "FROM favorites "
										+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
										+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
										+ "WHERE userId = ? "
										+ "AND gifts.isEnabled IS true "
										+ "AND recommenders.isEnabled IS true"
										, userId);

		List<FavGift> allFavGifts = new ArrayList<>();

		for(Map<String, Object> map: favGifts) {

			FavGift favGift = new FavGift();

			favGift.setFavoriteId((int)map.get("favoriteId"));
			favGift.setUserId((int)map.get("userId"));
			favGift.setGiftId((int)map.get("giftId"));
			favGift.setRecommenderName((String)map.get("recommenderName"));
			favGift.setGiftName((String)map.get("giftName"));
			favGift.setPrice((String)map.get("price"));
			favGift.setImage((String)map.get("image"));
			favGift.setDescription((String)map.get("description"));
			favGift.setShop((String)map.get("shop"));
			favGift.setAddress((String)map.get("address"));
			favGift.setPhone((String)map.get("phone"));

			allFavGifts.add(favGift);
		}
		return allFavGifts;
	}

	@Override
	public int existFavId(String userName, int giftId) throws EmptyResultDataAccessException {

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users "
							+ "WHERE userName = ? "
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		int favId = jdbc.queryForObject("SELECT favoriteId "
								+ "FROM favorites "
								+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
								+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
								+ "WHERE favorites.userId = ? "
								+ "AND favorites.giftId = ? "
								+ "AND gifts.isEnabled IS true "
								+ "AND recommenders.isEnabled IS true"
								, Integer.class, userId, giftId);

		return favId;
	}

	@Override
	public int count(String userName) throws EmptyResultDataAccessException {

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users "
							+ "WHERE userName = ? "
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		int favIds = jdbc.queryForObject("SELECT COUNT(favorites.favoriteId) "
							+ "FROM favorites "
							+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
							+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
							+ "WHERE favorites.userId = ? "
							+ "AND gifts.isEnabled IS true "
							+ "AND recommenders.isEnabled IS true"
							, Integer.class, userId);

		return favIds;
	}

	@Override
	public int create(String userName, int giftId) throws DataIntegrityViolationException, EmptyResultDataAccessException {

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users WHERE userName = ? "
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		int enabledGiftId = jdbc.queryForObject("SELECT giftId "
								+ "FROM gifts "
								+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
								+ "WHERE gifts.giftId = ?"
								+ "AND gifts.isEnabled IS true "
								+ "AND recommenders.isEnabled IS true"
								, Integer.class, giftId);

		int suceededRowNumber = jdbc.update("INSERT INTO favorites(userId, giftId) "
							+ "VALUES(?, ?)"
							, userId, enabledGiftId);

		return suceededRowNumber;
	}

	@Override
	public int delete(String userName, int giftId)throws EmptyResultDataAccessException {

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users "
							+ "WHERE userName = ? "
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		int suceededRowNumber = jdbc.update("DELETE "
							+ "FROM favorites "
							+ "USING gifts, recommenders "
							+ "WHERE favorites.giftId = gifts.giftId "
							+ "AND gifts.recommenderId = recommenders.recommenderId "
							+ "AND favorites.userId = ? "
							+ "AND favorites.giftId = ?"
							+ "AND gifts.isEnabled IS true "
							+ "AND recommenders.isEnabled IS true"
							, userId, giftId);

		return suceededRowNumber;
	}

	@Override
	public int deleteMany(String userName)throws DataAccessException{

		int userId = jdbc.queryForObject("SELECT userId "
							+ "FROM users "
							+ "WHERE userName = ?"
							+ "AND isEnabled IS true"
							, Integer.class, userName);

		int suceededRowNumber = jdbc.update("DELETE "
							+ "FROM favorites "
							+ "USING gifts, recommenders "
							+ "WHERE favorites.giftId = gifts.giftId "
							+ "AND gifts.recommenderId = recommenders.recommenderId "
							+ "AND favorites.userId = ? "
							+ "AND gifts.isEnabled IS true "
							+ "AND recommenders.isEnabled IS true"
							, userId);

		return suceededRowNumber;
	}
}