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

import com.example.demo.domain.model.favorite.Favorite;
import com.example.demo.domain.repository.FavoriteDao;

@Repository
public class FavoriteDaoJdbcImpl implements FavoriteDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Favorite> selectAll(String userName) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	List<Map<String, Object>> favorites = jdbc.queryForList("SELECT * "
								+ "FROM favGift "
								+ "INNER JOIN gift"
								+ " ON favGift.giftId = gift.giftId "
								+ "INNER JOIN guest "
								+ " ON gift.guestId = guest.guestId "
								+ "WHERE userId = ?"
								+ " AND favGift.unavailableFlag IS NULL"
								, userId);

	List<Favorite> allFavorites = new ArrayList<>();

	for (Map<String, Object> map : favorites) {

	    Favorite favorite = new Favorite();
	    favorite.setUserId((int) map.get("favId"));
	    favorite.setUserId((int) map.get("userId"));
	    favorite.setGiftId((int) map.get("giftId"));
	    favorite.setGuestName((String) map.get("guestName"));
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
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	int favoriteId = jdbc.queryForObject("SELECT favId "
					+ "FROM favGift "
					+ "WHERE userId = ?"
					+ " AND giftId = ? "
					+ " AND favGift.unavailableFlag IS NULL"
					, Integer.class, userId, giftId);

	return favoriteId;
    }

    @Override
    public int count(String userName) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	int favoriteIds = jdbc.queryForObject("SELECT COUNT(favId) "
					+ "FROM favGift "
					+ "WHERE userId = ?"
					+ " AND favGift.unavailableFlag IS NULL"
					, Integer.class, userId);

	return favoriteIds;
    }

    @Override
    public int createOne(String userName, int giftId) throws DataIntegrityViolationException, EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	int rowNumber = jdbc.update("INSERT INTO favGift(userId, giftId) "
						+ "VALUES(?, ?)"
						, userId, giftId);

	return rowNumber;
    }

    @Override
    public int deleteOne(String userName, int giftId) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	int rowNumber = jdbc.update("UPDATE favGift "
						+ "SET unavailableFlag = '1' "
						+ "WHERE userId = ?"
						+ " AND giftId = ?"
						+ " AND favGift.unavailableFlag IS NULL"
						, userId, giftId);

	return rowNumber;
    }

    @Override
    public int deleteMany(String userName) throws DataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM userData "
					+ "WHERE userName = ?"
					, Integer.class, userName);

	int rowNumber = jdbc.update("UPDATE favGift "
						+ "SET unavailableFlag = '1' "
						+ "WHERE userId = ?"
						+ " AND unavailableFlag IS NULL"
						, userId);

	return rowNumber;
    }
}