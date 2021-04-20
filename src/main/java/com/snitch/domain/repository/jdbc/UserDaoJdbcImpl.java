package com.snitch.domain.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.snitch.domain.model.user.User;
import com.snitch.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int createOne(User user) throws DuplicateKeyException {

	String password = passwordEncoder.encode(user.getPassword());

	String sql = "INSERT INTO users("
				+ " userName,"
				+ " mailAddress,"
				+ " password,"
				+ " role)"
				+ " VALUES(?, ?, ?, ?)";

	int rowNumber = jdbc.update(sql, user.getUserName(), user.getMailAddress(), password, user.getRole());

	return rowNumber;
    }

    @Override
    public int updateOne(User user, String userName_LoggedIn) throws DuplicateKeyException {

	String password = passwordEncoder.encode(user.getPassword());

	String sql = "UPDATE users "
			+ "SET userName = ?, mailAddress = ?, password = ? "
			+ "WHERE userName = ? "
			+ "AND isEnabled IS true";

	int rowNumber = jdbc.update(sql, user.getUserName(), user.getMailAddress(), password, userName_LoggedIn);

	return rowNumber;
    }

    @Override
    public User selectOne(String userName) throws EmptyResultDataAccessException {

	Map<String, Object> singleUser = jdbc.queryForMap("SELECT * "
							+ "FROM users "
							+ "WHERE userName = ? "
							+ "AND isEnabled IS true"
							, userName);
	User user = new User();
	user.setUserName((String) singleUser.get("userName"));
	user.setMailAddress((String) singleUser.get("mailAddress"));
	user.setPassword((String) singleUser.get("password"));

	return user;
    }

    @Override
    public int deleteOne(String userName) throws EmptyResultDataAccessException {

	int userId = jdbc.queryForObject("SELECT userId "
					+ "FROM users "
					+ "WHERE userName = ? "
					+ "AND isEnabled IS true"
					, Integer.class, userName);

	int rowNumber = jdbc.update("UPDATE users "
					+ "SET isEnabled = 'false' "
					+ "WHERE userId = ? "
					+ "AND isEnabled IS true"
					, userId);

	return rowNumber;
    }

    @Override
    public int exist(String userName) throws DataAccessException {

	return jdbc.queryForObject("SELECT COUNT(userName) "
					+ "FROM users "
					+ "WHERE userName = ?"
					, Integer.class, userName);
    }

    public User search(String userName) throws DataAccessException {

	Map<String, Object> map = jdbc.queryForMap("SELECT * "
							+ "FROM users "
							+ "WHERE userName = ?"
							, userName);
	User user = new User();
	user.setUserName((String) map.get("userName"));
	user.setMailAddress((String) map.get("mailAddress"));
	user.setPassword((String) map.get("password"));

	return user;
    }
}