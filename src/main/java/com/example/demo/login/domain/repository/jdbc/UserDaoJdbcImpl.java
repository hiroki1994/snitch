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

		return rowNumber;
	}

	@Override
	public User selectOne(String userName) throws DataAccessException {

		Map<String, Object> singleUser = jdbc.queryForMap("SELECT * FROM userData WHERE userName = ?", userName);

		User user = new User();

		user.setUserName((String)singleUser.get("userName"));
		user.setMailAddress((String)singleUser.get("mailAddress"));
		user.setPassword((String)singleUser.get("password"));

		return user;
	}

	@Override
	public int deleteOne(String userName) throws DataAccessException {

		int userId = jdbc.queryForObject("SELECT userId FROM userData WHERE userName = ?", Integer.class, userName);

		int rowNumber = jdbc.update("UPDATE userData SET unavailableFlag = '1' WHERE userId = ? AND unavailableFlag IS NULL", userId);

		return rowNumber;
	}

	@Override
	public int exist(String userName) throws DataAccessException {

		return jdbc.queryForObject("SELECT COUNT(userName) FROM userData WHERE userName = ?", Integer.class, userName);
	}

	public User findUser(String userName) throws DataAccessException {

		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM userData WHERE userName = ?", userName);

		User user = new User();

		user.setUserName((String)map.get("userName"));
		user.setMailAddress((String)map.get("mailAddress"));
		user.setPassword((String)map.get("password"));

		return user;
	}
}
