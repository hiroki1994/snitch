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

//お気に入り用リポジトリークラス
@Repository
public class FavGiftDaoJdbcImpl implements FavGiftDao {

	@Autowired
	JdbcTemplate jdbc;


	//お気に入り全件取得用メソッド
	@Override
	public List<FavGift> selectMany(String userName) throws DataAccessException {

		Map<String, Object> mapForID = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);



			//ログイン中のuserIdがカラム「userId」中に含まれているデータを全件取得し、リストに格納
			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM favGift INNER JOIN gift ON favGift.giftId = gift.giftId INNER JOIN guest ON gift.guestId = guest.guestId WHERE userId = ? AND favGift.unavailableFlag IS NULL", mapForID.get("userId"));

			List<FavGift> favGiftList = new ArrayList<>();

			//繰り返し処理でリストに格納したデータを「favOmiyage」にセット
			for(Map<String, Object> map: getList) {

				//FavGiftインスタンスの生成
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


				//「favOmiyage」をリスト「favOmiyageList」に格納
				favGiftList.add(favGift);
			}
			return favGiftList;
		}

	//お気に入り登録時に発行されるIDの有無を確認
	@Override
	public int searchFavId(String userName, int giftId) throws DataAccessException {




		/*
		 * 該当のIDと一致するIDを含むお土産のレコードをテーブル「FavOmiyage」より取得
		 */
		try {
		Map<String, Object> mapForID = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);


		Map<String, Object> favIdFind = jdbc.queryForMap("SELECT favId FROM favGift WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", mapForID.get("userId"), giftId);

		int favId2 =  (int) favIdFind.get("favId");

		return favId2;

		} catch(DataAccessException e) {


		int favId2 = 0;
		return favId2;
		}


	}

	//お気に入り件数取得用メソッド
	@Override
	public int count(String userName) throws DataAccessException {

		Map<String, Object> mapForID = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		//ログイン中のuserIdがカラム「UserId」中に含まれているデータのomiyaIDを全件取得し、リストに格納
		List<Map<String, Object>>  getList = jdbc.queryForList("SELECT giftId FROM favGift WHERE userId = ? AND favGift.unavailableFlag IS NULL", mapForID.get("userId"));

		//要素数をカウント
		int favCount = getList.size();


		return favCount;
	}

	//お気に入り登録用のメソッド
	@Override
	public int insert(String userName, int giftId) throws DataAccessException {

		Map<String, Object> mapForID = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);



		//mapから値を取得しSQL文の？に値をセットし、SQL文実行
		int rowNumber = jdbc.update("INSERT INTO favGift(userId, giftId) VALUES(?, ?)", mapForID.get("userId"), giftId);



		return rowNumber;
	}

	//お気に入り削除用のメソッド　ユーザー退会時にメソッドが実行される
	@Override
	public int delete(String userName, int giftId)throws DataAccessException{

		Map<String, Object> mapForID = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		//お気に入りテーブルのユニークID「favId」で登録されたお土産をお気に入りから削除
		int rowNumber = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND giftId = ? AND favGift.unavailableFlag IS NULL", mapForID.get("userId"), giftId);

		return rowNumber;
	}



}
