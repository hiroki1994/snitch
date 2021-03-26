package com.example.demo.login.domain.repository;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

	public int create(User user) throws DataAccessException, SQLException;

	public int updateOne(User user, String userName_LoggedIn) throws DataAccessException, SQLException;

	public User selectOne(String userName) throws DataAccessException;

	public int deleteOne(String userName) throws EmptyResultDataAccessException;

	public int exist(String userName) throws DataAccessException;
}
