package com.example.demo.login.domain.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public int insertOne(User user) throws DataAccessException {

		String password = passwordEncoder.encode(user.getPassword());

		String sql = "INSERT INTO userData("
			+ " userName,"
			+ " mailAddress,"
			+ " password,"
			+ " role)"
			+ " VALUES(?, ?, ?, ?)";

		int rowNumber = jdbc.update(sql,
			user.getUserName(),
			user.getMailAddress(),
			password,
			user.getRole());

		return rowNumber;
	}

	@Override
	public int updateOne(User user, String userName_LoggedIn) throws DataAccessException {

		String password = passwordEncoder.encode(user.getPassword());

		String sql = "UPDATE userData SET"
			+ " userName = ?,"
			+ " mailAddress = ?,"
			+ " password = ?"
			+ " WHERE"
			+ " userName = ?";

		int rowNumber = jdbc.update(sql,
				user.getUserName(),
				user.getMailAddress(),
				password,
				userName_LoggedIn);

		System.out.println(user.getUserName()+"へ変更");

		return rowNumber;
	}

	@Override
	public User selectOne(String userName) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM userData WHERE userName = ?", userName);

		User user = new User();

		System.out.println(map.get("userId"));

		user.setUserName((String)map.get("userName"));
		user.setMailAddress((String)map.get("mailAddress"));
		user.setPassword((String)map.get("password"));

		return user;
	}

	@Override
	public int deleteOne(String userName) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT userId FROM userData WHERE userName = ?", userName);

		int rowNumber = jdbc.update("UPDATE userData SET unavailableFlag = '1' WHERE userId = ? AND unavailableFlag IS NULL", map.get("userId"));

		int rowNumber2 = jdbc.update("UPDATE favGift SET unavailableFlag = '1' WHERE userId = ? AND unavailableFlag IS NULL", map.get("userId"));

        if(rowNumber2 > 0) {
        	System.out.println("お気に入り削除完了");
        } else {
        	System.out.println("お気に入り削除失敗");
        }

		return rowNumber;
	}

}




