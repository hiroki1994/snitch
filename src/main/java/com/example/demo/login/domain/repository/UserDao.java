package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {

	public int create(User user) throws DuplicateKeyException;

	public int update(User user, String userName_LoggedIn) throws DuplicateKeyException;

	public User select(String userName) throws EmptyResultDataAccessException;

	public int delete(String userName) throws EmptyResultDataAccessException;

	public int exist(String userName) throws DataAccessException;
}
