package com.example.demo.login.domain.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

//ユーザー用リポジトリークラス
@Repository
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	//SecurityConfigでBeanアノテーションをAutowiredする
	@Autowired
	PasswordEncoder passwordEncoder;

	//ユーザー新規登録用メソッド
	@Override
	public int insertOne(User user) throws DataAccessException {

		//Userに格納されたpasswordを暗号化 BCryptによる暗号化
		String password = passwordEncoder.encode(user.getPassword());

		//PreparedStatementを使って登録用のSQL文を作成
		String sql = "INSERT INTO userData("
			+ " userId,"
			+ " mailAddress,"
			+ " password,"
			+ " role)"
			+ " VALUES(?, ?, ?, ?)";

		//Userから値を取得し、SQL文の？に値をセットし、SQL文実行
		int rowNumber = jdbc.update(sql,
			user.getUserId(),
			user.getMailAddress(),
			password,
			user.getRole());

		return rowNumber;
	}


	//ログイン済みのユーザーの登録情報を更新
	@Override
	public int updateOne(User user, String userId_LoggedIn) throws DataAccessException {

		//Userに格納されたpasswordを暗号化 BCryptによる暗号化
		String password = passwordEncoder.encode(user.getPassword());

		//PreparedStatementを使って更新用のSQL文を作成
		String sql = "UPDATE userData SET"
			+ " userId = ?,"
			+ " mailAddress = ?,"
			+ " password = ?"
			+ " WHERE"
			+ " userId = ?";

		//Userから値を取得し、SQL文の？に値をセットし、SQL文実行
		int rowNumber = jdbc.update(sql,
				user.getUserId(),
				user.getMailAddress(),
				password,
				userId_LoggedIn);


		return rowNumber;
	}

	//ユーザー登録情報更新ページ表示用メソッド ログイン済みのIDで1件検索
	@Override
	public User selectOne(String userId) throws DataAccessException {



		//ログイン済みIDでテーブル「userData」の中から該当のユーザーデータのレコードを取得し、マップに格納
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM userData" + " WHERE userId = ?", userId);

		User user = new User();

		//マップに格納された要素をUserに格納
		user.setUserId((String)map.get("userId"));
		user.setMailAddress((String)map.get("mailAddress"));
		user.setPassword((String)map.get("password"));



		return user;

	}

	//退会用メソッド
	@Override
	public int deleteOne(String userId) throws DataAccessException {

		//ユーザーのテーブルのユーザーデータの削除
		int rowNumber = jdbc.update("DELETE FROM userData WHERE userId = ?", userId);

		//お気に入りのお土産テーブルからデータを削除
		int rowNumber2 = jdbc.update("DELETE FROM FavOmiyage WHERE userId = ?", userId);

		//お気に入り削除機能の結果をコンソールに表示
        if(rowNumber2 > 0) {
        	System.out.println("お気に入り削除完了");
        } else {
        	System.out.println("お気に入り削除失敗");
        }

		return rowNumber;
	}

}




