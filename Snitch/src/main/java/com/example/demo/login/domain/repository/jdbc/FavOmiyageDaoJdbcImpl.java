package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.FavOmiyage;
import com.example.demo.login.domain.repository.FavOmiyageDao;

//お気に入り用リポジトリークラス
@Repository
public class FavOmiyageDaoJdbcImpl implements FavOmiyageDao {

	@Autowired
	JdbcTemplate jdbc;


	//お気に入り全件取得用メソッド
	@Override
	public List<FavOmiyage> selectMany(String userId) throws DataAccessException {

			//ログイン中のUserIdがカラム「userId」中に含まれているデータを全件取得し、リストに格納
			List<Map<String, Object>>  getList = jdbc.queryForList("select * from FavOmiyage" + " where userId = ?", userId);

			List<FavOmiyage> favOmiyageList = new ArrayList<>();

			//繰り返し処理でリストに格納したデータを「favOmiyage」にセット
			for(Map<String, Object> map: getList) {

				//FavOmiyageインスタンスの生成
				FavOmiyage favOmiyage = new FavOmiyage();

				System.out.println((String)map.get("userId"));
				favOmiyage.setUserId((String)map.get("favId"));
				favOmiyage.setUserId((String)map.get("userId"));
				favOmiyage.setOmiyaID((int)map.get("omiyaID"));
				favOmiyage.setGuest((String)map.get("guest"));
				favOmiyage.setName((String)map.get("name"));
				favOmiyage.setPrice((String)map.get("price"));
				favOmiyage.setImage((String)map.get("image"));
				favOmiyage.setDescription((String)map.get("description"));
				favOmiyage.setShop((String)map.get("shop"));
				favOmiyage.setAddress((String)map.get("address"));
				favOmiyage.setPhone((String)map.get("phone"));
				System.out.println((String)map.get("phone"));

				//「favOmiyage」をリスト「favOmiyageList」に格納
				favOmiyageList.add(favOmiyage);
			}
			return favOmiyageList;
		}

	//お気に入り登録時に発行されるIDの有無を確認
	@Override
	public String searchFavId(String favId) throws DataAccessException {


		//SQL文の作成
		String sql =  "select * from FavOmiyage where favId = ?";

		/*
		 * 該当のIDと一致するIDを含むお土産のレコードをテーブル「FavOmiyage」より取得
		 */
		try {
		Map<String, Object> favIdFind = jdbc.queryForMap(sql, favId);

		String favId2 = (String) favIdFind.get("favId");

		return favId2;

		} catch(DataAccessException e) {
			return null;
		}


	}

	//お気に入り件数取得用メソッド
	@Override
	public int count(String userId) throws DataAccessException {

		//ログイン中のuserIdがカラム「UserId」中に含まれているデータのomiyaIDを全件取得し、リストに格納
		List<Map<String, Object>>  getList = jdbc.queryForList("select omiyaID from FavOmiyage" + " where userId = ?", userId);

		//要素数をカウント
		int favCount = getList.size();


		return favCount;
	}

	//お気に入り登録用のメソッド
	@Override
	public int insert(int favOmiyaID, String userId) throws DataAccessException {

		//お土産テーブルの中から指定のID「favOmiyaID」と一致したレコードをマップに登録
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM omiyage" + " WHERE omiyaID = ?", favOmiyaID);

		//お気に入り追加されたお土産のユニークIDを作成
		String favId = userId + map.get("omiyaID");

		//データ登録用のSQL文をPreparedStatementで作成
		String sql = "INSERT INTO FavOmiyage("
				+ " favId,"
				+ " userId,"
				+ " omiyaID,"
				+ " guest,"
				+ " name,"
				+ " price,"
				+ " image,"
				+ " description,"
				+ " shop,"
				+ " address,"
				+ " phone)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			//mapから値を取得しSQL文の？に値をセットし、SQL文実行
			int rowNumber = jdbc.update(sql,
				favId,
				userId,
				(int)map.get("omiyaID"),
				(String)map.get("guest"),
				(String)map.get("name"),
				(String)map.get("price"),
				(String)map.get("image"),
				(String)map.get("description"),
				(String)map.get("shop"),
				(String)map.get("address"),
				(String)map.get("phone"));


			return rowNumber;
	}

	//お気に入り削除用のメソッド　ユーザー退会時にメソッドが実行される
	@Override
	public int delete(String favId)throws DataAccessException{

		//お気に入りテーブルのユニークID「favId」で登録されたお土産をお気に入りから削除
		int rowNumber = jdbc.update("DELETE FROM FavOmiyage WHERE favId = ?", favId);

		return rowNumber;
	}



}
