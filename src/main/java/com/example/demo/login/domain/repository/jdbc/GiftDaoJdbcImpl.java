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


//お土産用リポジトリークラス
@Repository
public class GiftDaoJdbcImpl implements GiftDao {


	//SpringですでにBean定義されているJdbctemplateを@Autowiredして使用可能にする
	@Autowired
	JdbcTemplate jdbc;

	//検索結果件数取得用メソッド
	@Override
	public int count(String keyword) throws DataAccessException {

		//検索キーワード「keyword」をカラム「keyword」に含むお土産の登録IDのみを取得し、リストに格納
		List<Map<String, Object>>  getList = jdbc.queryForList("SELECT giftId FROM gift INNER JOIN guest ON gift.guestId = guest.guestId "
				+ "WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag = 0", keyword);

		int searchCount = 0;

		/*
		 * リストに格納された要素数を取得
		 * html側で表示切り替えをするためにnullの場合は「-1」を変数に代入
		 */
        if(getList != null) {
        	searchCount= getList.size();
        } else if(getList == null){
        	searchCount = -1;
        }
		return searchCount;
	}

	//検索キーワード「keyword」をカラム「keyword」に含むお土産を取得しリストに格納
	@Override
	public List<Gift> search(String keyword) throws DataAccessException {


			//検索キーワード「keyword」登録情報内に含むお土産の登録IDのみを取得し、リストに格納
			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId" + " WHERE CONCAT(giftName, description, shop, address, guestName) LIKE '%'||?||'%' AND gift.unavailableFlag = 0", keyword);


			List<Gift> giftList = new ArrayList<>();

			//繰り返し処理で「omiyage」にリストに格納されたデータをsetする
			for(Map<String, Object> map: getList) {

				//Giftインスタンスの生成
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


				//「omiyageList」リストに「omiyage」を格納
				giftList.add(gift);
			}
			return giftList;
		}

	//home画面表示用メソッド
	@Override
	public List<Gift> selectMany() throws DataAccessException {

			//テーブル「gift」の中からランダムで27件取得
			List<Map<String, Object>>  getList = jdbc.queryForList("SELECT * FROM gift INNER JOIN guest ON gift.guestId = guest.guestId WHERE gift.unavailableFlag = 0 ORDER BY RANDOM() LIMIT 27");

			List<Gift> giftList = new ArrayList<>();

			//繰り返し処理で「omiyage」にリストに格納されたデータをsetする
			for(Map<String, Object> map: getList) {

				//Omiyageインスタンスの生成
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


				//「omiyageList」リストに「omiyage」を格納
				giftList.add(gift);
			}
			return giftList;
		}

	//詳細画面表示用メソッド
	@Override
	public Gift selectOne(int giftId) throws DataAccessException {

		//カラム「giftId」の中から、該当のgiftIdと一致するお土産を検索。取得したレコードをマップに格納
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM gift" + " WHERE giftId = ? AND gift.unavailableFlag = 0", giftId);

		//Omiyageインスタンスを生成
		Gift gift = new Gift();

		//omiyageにマップに格納されたデータをset
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
