package com.snitch.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.snitch.domain.model.favorite.Favorite;
import com.snitch.domain.repository.FavoriteDao;

@Repository
public class FavoriteDaoJdbcImpl implements FavoriteDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Favorite> selectAll(String userName) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ? "
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	List<Map<String, Object>> favorites = jdbc.queryForList("SELECT * "
								+ "FROM favorites "
								+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
								+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
								+ "WHERE userId = ? "
								+ "AND gifts.isEnabled IS true "
								+ "AND recommenders.isEnabled IS true"
								, userId);

	List<Favorite> allFavorites = new ArrayList<>();

	for (Map<String, Object> map : favorites) {

	    Favorite favorite = new Favorite();
	    favorite.setFavoriteId((int) map.get("favoriteId"));
	    favorite.setUserId((int) map.get("userId"));
	    favorite.setGiftId((int) map.get("giftId"));
	    favorite.setRecommenderName((String) map.get("recommenderName"));
	    favorite.setGiftName((String) map.get("giftName"));
	    favorite.setPrice((String) map.get("price"));
	    favorite.setImage((String) map.get("image"));
	    favorite.setDescription((String) map.get("description"));
	    favorite.setShop((String) map.get("shop"));
	    favorite.setAddress((String) map.get("address"));
	    favorite.setPhone((String) map.get("phone"));
	    allFavorites.add(favorite);
	}
	return allFavorites;
    }

    @Override
    public int exist(String userName, int giftId) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ? "
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	int favoriteId = jdbc.queryForObject("SELECT favoriteId "
						+ "FROM favorites "
						+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
						+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
						+ "WHERE favorites.userId = ? "
						+ "AND favorites.giftId = ? "
						+ "AND gifts.isEnabled IS true "
						+ "AND recommenders.isEnabled IS true"
						, Integer.class, userId, giftId);

	return favoriteId;
    }

    @Override
    public int count(String userName) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ? "
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	int favoriteIds = jdbc.queryForObject("SELECT COUNT(favorites.favoriteId) "
						+ "FROM favorites "
						+ "INNER JOIN gifts ON favorites.giftId = gifts.giftId "
						+ "INNER JOIN recommenders ON gifts.recommenderId = recommenders.recommenderId "
						+ "WHERE favorites.userId = ? "
						+ "AND gifts.isEnabled IS true "
						+ "AND recommenders.isEnabled IS true"
						, Integer.class, userId);

	return favoriteIds;
    }

    @Override
    public int createOne(String userName, int giftId) throws DataIntegrityViolationException, EmptyResultDataAccessException {

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

	int rowNumber = jdbc.update("INSERT INTO favorites("
					+ "userId, "
					+ "giftId) "
					+ "VALUES(?, ?)"
					, userId, enabledGiftId);

	return rowNumber;
    }

    @Override
    public int deleteOne(String userName, int giftId) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ? "
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	int rowNumber = jdbc.update("DELETE "
					+ "FROM favorites "
					+ "USING gifts, recommenders "
					+ "WHERE favorites.giftId = gifts.giftId "
					+ "AND gifts.recommenderId = recommenders.recommenderId "
					+ "AND favorites.userId = ? "
					+ "AND favorites.giftId = ?"
					+ "AND gifts.isEnabled IS true "
					+ "AND recommenders.isEnabled IS true"
					, userId, giftId);

	return rowNumber;
    }

    @Override
    public int deleteMany(String userName) throws DataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ?"
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	int rowNumber = jdbc.update("DELETE "
					+ "FROM favorites "
					+ "USING gifts, recommenders "
					+ "WHERE favorites.giftId = gifts.giftId "
					+ "AND gifts.recommenderId = recommenders.recommenderId "
					+ "AND favorites.userId = ? "
					+ "AND gifts.isEnabled IS true "
					+ "AND recommenders.isEnabled IS true"
					, userId);

	return rowNumber;
    }
}