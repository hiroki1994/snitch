package com.snitch.domain.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.snitch.domain.model.user.User;

public interface UserDao {

    public int createOne(User user) throws DuplicateKeyException;

    public int updateOne(User user, String userName_LoggedIn) throws DuplicateKeyException;

    public User selectOne(String userName) throws EmptyResultDataAccessException;

    public int deleteOne(String userName) throws EmptyResultDataAccessException;

    public int exist(String userName) throws DataAccessException;
}