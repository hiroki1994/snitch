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

		Map<String, Object> userId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

			List<Map<String, Object>> favGifts = jdbc.queryForList("SELECT * FROM favGift INNER JOIN gift ON favGift.giftId = gift.giftId INNER JOIN guest ON gift.guestId = guest.guestId WHERE userId = ? AND favGift.unavailableFlag IS NULL", userId.get("userId"));

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
	public int searchFavId(String userName, int giftId) throws DataAccessException {

		try {
			Map<String, Object> userId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

			Map<String, Object> favId = jdbc.queryForMap("SELECT favId FROM favGift WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", userId.get("userId"), giftId);

			int castedFavId =  (int) favId.get("favId");

			return castedFavId;

		} catch(DataAccessException e) {
			int favId = 0;
			return favId;
		}


	}

	@Override
	public int count(String userName) throws DataAccessException {

		Map<String, Object> userId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		List<Map<String, Object>> giftIds = jdbc.queryForList("SELECT giftId FROM favGift WHERE userId = ? AND favGift.unavailableFlag IS NULL", userId.get("userId"));

		return giftIds.size();
	}

	@Override
	public int create(String userName, int giftId) throws DataAccessException {

		Map<String, Object> userId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		int suceededRowNumber = jdbc.update("INSERT INTO favGift(userId, giftId) VALUES(?, ?)", userId.get("userId"), giftId);

		return suceededRowNumber;
	}

	@Override
	public int delete(String userName, int giftId)throws DataAccessException{

		Map<String, Object> userId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		int suceededRowNumber = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", userId.get("userId"), giftId);

		return suceededRowNumber;
	}



}
