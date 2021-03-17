package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

	public int insertOne(User user) throws DataAccessException;

	public int updateOne(User user, String userName_LoggedIn) throws DataAccessException;

	public User selectOne(String userName) throws DataAccessException;

	public int deleteOne(String userName) throws DataAccessException;

	public int exist(String userName) throws DataAccessException;
}
