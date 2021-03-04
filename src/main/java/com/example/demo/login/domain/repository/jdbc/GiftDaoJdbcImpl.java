package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.repository.GiftDao;


@Repository
public class GiftDaoJdbcImpl implements GiftDao {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public int count(String keyword) throws DataAccessException {

		List<Map<String, Object>>  getList = jdbc.queryForList("SELECT giftId FROM gift INNER JOIN guest ON gift.guestId = guest.guestId WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag IS NULL", keyword);

		int searchCount = 0;

        if(getList != null) {
        	searchCount= getList.size();
        } else if(getList == null){
        	searchCount = -1;
        }
		return searchCount;
	}

	//SQLのCOUNTでいいのでは??

	@Override
	public List<Gift> search(String keyword) throws DataAccessException {

			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag IS NULL", keyword);

			List<Gift> giftList = new ArrayList<>();

			for(Map<String, Object> map: getList) {

				Gift gift = new Gift();

				gift.setGiftId((int)map.get("giftId"));
				gift.setGuestName((String)map.get("guestName"));
				gift.setGiftName((String)map.get("giftName"));
				gift.setPrice((String)map.get("price"));
				gift.setImage((String)map.get("image"));
				gift.setDescription((String)map.get("description"));
				gift.setShop((String)map.get("shop"));
				gift.setAddress((String)map.get("address"));
				gift.setPhone((String)map.get("phone"));

				giftList.add(gift);
			}
			return giftList;
		}

	@Override
	public List<Gift> selectMany() throws DataAccessException {

			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId WHERE gift.unavailableFlag IS NULL ORDER BY RANDOM() LIMIT 27");

			List<Gift> giftList = new ArrayList<>();

			for(Map<String, Object> map: getList) {

				Gift gift = new Gift();

				gift.setGiftId((int)map.get("giftId"));
				gift.setGuestName((String)map.get("guestName"));
				gift.setGiftName((String)map.get("giftName"));
				gift.setPrice((String)map.get("price"));
				gift.setImage((String)map.get("image"));
				gift.setDescription((String)map.get("description"));
				gift.setShop((String)map.get("shop"));
				gift.setAddress((String)map.get("address"));
				gift.setPhone((String)map.get("phone"));

				giftList.add(gift);
			}
			return giftList;
		}

	@Override
	public Gift selectOne(int giftId) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId WHERE giftId = ? AND gift.unavailableFlag IS NULL", giftId);

		Gift gift = new Gift();

		gift.setGiftId((int)map.get("giftId"));
		gift.setGuestName((String)map.get("guestName"));
		gift.setGiftName((String)map.get("giftName"));
		gift.setPrice((String)map.get("price"));
		gift.setImage((String)map.get("image"));
		gift.setDescription((String)map.get("description"));
		gift.setShop((String)map.get("shop"));
		gift.setAddress((String)map.get("address"));
		gift.setPhone((String)map.get("phone"));

		return gift;
	}


}
