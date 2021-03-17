package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;

@Repository
public class FavGiftDaoJdbcImpl implements FavGiftDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<FavGift> selectAll(String userName) throws DataAccessException {

		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

			List<Map<String, Object>> favGifts = jdbc.queryForList("SELECT * FROM favGift INNER JOIN gift ON favGift.giftId = gift.giftId INNER JOIN guest ON gift.guestId = guest.guestId WHERE userId = ? AND favGift.unavailableFlag IS NULL", userId);

			List<FavGift> allFavGifts = new ArrayList<>();

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

				allFavGifts.add(favGift);
			}
			return allFavGifts;
		}

	@Override
	public int existFavId(String userName, int giftId) throws DataAccessException {

			int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

			int favId = jdbc.queryForObject("SELECT favId FROM favGift WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", Integer.class, userId, giftId);

			return favId;

	}

	@Override
	public int count(String userName) throws DataAccessException {

		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

		int favIds = jdbc.queryForObject("SELECT COUNT(favId) FROM favGift WHERE userId = ? AND favGift.unavailableFlag IS NULL", Integer.class, userId);

		return favIds;
	}

	@Override
	public int create(String userName, int giftId) throws DataAccessException {


		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

		int suceededRowNumber = jdbc.update("INSERT INTO favGift(userId, giftId) VALUES(?, ?)", userId, giftId);

		System.out.println(suceededRowNumber);

		return suceededRowNumber;
	}

	@Override
	public int delete(String userName, int giftId)throws DataAccessException{

		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

		int suceededRowNumber = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", userId, giftId);

		return suceededRowNumber;
	}

	@Override
	public int deleteMany(String userName)throws DataAccessException{

		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

		int suceededRowNumber = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND unavailableFlag IS NULL", userId);

		return suceededRowNumber;
	}
}
