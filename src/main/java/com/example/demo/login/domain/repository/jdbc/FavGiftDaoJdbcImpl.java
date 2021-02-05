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
	public List<FavGift> selectMany(String userName) throws DataAccessException {

		Map<String, Object> mapForUserId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM favGift INNER JOIN gift ON favGift.giftId = gift.giftId INNER JOIN guest ON gift.guestId = guest.guestId WHERE userId = ? AND favGift.unavailableFlag IS NULL", mapForUserId.get("userId"));

			List<FavGift> favGiftList = new ArrayList<>();

			for(Map<String, Object> map: getList) {

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

				favGiftList.add(favGift);
			}
			return favGiftList;
		}

	@Override
	public int searchFavId(String userName, int giftId) throws DataAccessException {

		try {
			Map<String, Object> mapForUserId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

			Map<String, Object> favIdFind = jdbc.queryForMap("SELECT favId FROM favGift WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", mapForUserId.get("userId"), giftId);

			int favId2 =  (int) favIdFind.get("favId");

			return favId2;

		} catch(DataAccessException e) {
			int favId2 = 0;
			return favId2;
		}


	}

	@Override
	public int count(String userName) throws DataAccessException {

		Map<String, Object> mapForUserId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		List<Map<String, Object>>  getList = jdbc.queryForList("SELECT giftId FROM favGift WHERE userId = ? AND favGift.unavailableFlag IS NULL", mapForUserId.get("userId"));

		int favCount = getList.size();

		return favCount;
	}

	@Override
	public int addGiftToFav(String userName, int giftId) throws DataAccessException {

		System.out.println(userName+"この情報で検索");

		Map<String, Object> mapForUserId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		int favGiftRowNumber = jdbc.update("INSERT INTO favGift(userId, giftId) VALUES(?, ?)", mapForUserId.get("userId"), giftId);

		return favGiftRowNumber;
	}

	@Override
	public int deleteGiftFromFav(String userName, int giftId)throws DataAccessException{

		Map<String, Object> mapForUserId = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		int favGiftRowNumber = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", mapForUserId.get("userId"), giftId);

		return favGiftRowNumber;
	}



}
