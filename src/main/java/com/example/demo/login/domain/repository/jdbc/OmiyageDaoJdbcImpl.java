package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.Omiyage;
import com.example.demo.login.domain.repository.OmiyageDao;


//お土産用リポジトリークラス
@Repository
public class OmiyageDaoJdbcImpl implements OmiyageDao {


	//SpringですでにBean定義されているJdbctemplateを@Autowiredして使用可能にする
	@Autowired
	JdbcTemplate jdbc;

	//検索結果件数取得用メソッド
	@Override
	public int count(String keyword) throws DataAccessException {

		//検索キーワード「keyword」をカラム「keyword」に含むお土産の登録IDのみを取得し、リストに格納
		List<Map<String, Object>>  getList = jdbc.queryForList("select omiyaID from omiyage" + " where keyword LIKE '%'||?||'%'", keyword);

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
	public List<Omiyage> search(String keyword) throws DataAccessException {


			//検索キーワード「keyword」登録情報内に含むお土産の登録IDのみを取得し、リストに格納
			List<Map<String, Object>>  getList = jdbc.queryForList("select * from omiyage" + " where keyword LIKE '%'||?||'%'", keyword);


			List<Omiyage> omiyageList = new ArrayList<>();

			//繰り返し処理で「omiyage」にリストに格納されたデータをsetする
			for(Map<String, Object> map: getList) {

				//Omiyageインスタンスの生成
				Omiyage omiyage = new Omiyage();

				omiyage.setOmiyaID((int)map.get("omiyaID"));
				omiyage.setGuest((String)map.get("guest"));
				omiyage.setName((String)map.get("name"));
				omiyage.setPrice((String)map.get("price"));
				omiyage.setImage((String)map.get("image"));
				omiyage.setDescription((String)map.get("description"));
				omiyage.setShop((String)map.get("shop"));
				omiyage.setAddress((String)map.get("address"));
				omiyage.setPhone((String)map.get("phone"));
				omiyage.setKeyword((String)map.get("keyword"));

				//「omiyageList」リストに「omiyage」を格納
				omiyageList.add(omiyage);
			}
			return omiyageList;
		}

	//home画面表示用メソッド
	@Override
	public List<Omiyage> selectMany() throws DataAccessException {

			//テーブル「omiyage」の中からランダムで27件取得
			List<Map<String, Object>>  getList = jdbc.queryForList("select * from omiyage order by random() limit 27");

			List<Omiyage> omiyageList = new ArrayList<>();

			//繰り返し処理で「omiyage」にリストに格納されたデータをsetする
			for(Map<String, Object> map: getList) {

				//Omiyageインスタンスの生成
				Omiyage omiyage = new Omiyage();

				omiyage.setOmiyaID((int)map.get("omiyaID"));
				omiyage.setGuest((String)map.get("guest"));
				omiyage.setName((String)map.get("name"));
				omiyage.setPrice((String)map.get("price"));
				omiyage.setImage((String)map.get("image"));
				omiyage.setDescription((String)map.get("description"));
				omiyage.setShop((String)map.get("shop"));
				omiyage.setAddress((String)map.get("address"));
				omiyage.setPhone((String)map.get("phone"));
				omiyage.setKeyword((String)map.get("keyword"));

				//「omiyageList」リストに「omiyage」を格納
				omiyageList.add(omiyage);
			}
			return omiyageList;
		}

	//詳細画面表示用メソッド
	@Override
	public Omiyage selectOne(int omiyaID) throws DataAccessException {

		//カラム「omiyaID」の中から、該当のomiyaIDと一致するお土産を検索。取得したレコードをマップに格納
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM omiyage" + " WHERE omiyaID = ?", omiyaID);

		//Omiyageインスタンスを生成
		Omiyage omiyage = new Omiyage();

		//omiyageにマップに格納されたデータをset
		omiyage.setOmiyaID((int)map.get("omiyaID"));
		omiyage.setGuest((String)map.get("guest"));
		omiyage.setName((String)map.get("name"));
		omiyage.setPrice((String)map.get("price"));
		omiyage.setImage((String)map.get("image"));
		omiyage.setDescription((String)map.get("description"));
		omiyage.setShop((String)map.get("shop"));
		omiyage.setAddress((String)map.get("address"));
		omiyage.setPhone((String)map.get("phone"));


		return omiyage;
	}


}
