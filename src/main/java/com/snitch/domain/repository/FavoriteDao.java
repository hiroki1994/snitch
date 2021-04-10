package com.snitch.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.snitch.domain.model.favorite.Favorite;

public interface FavoriteDao {

    public List<Favorite> selectAll(String userName) throws EmptyResultDataAccessException;

    public int count(String userName) throws EmptyResultDataAccessException;

    public int createOne(String userName, int giftId) throws DataIntegrityViolationException, EmptyResultDataAccessException;

    public int exist(String userName, int giftId) throws EmptyResultDataAccessException;

    public int deleteOne(String userName, int giftId) throws EmptyResultDataAccessException;

    public int deleteMany(String userName) throws DataAccessException;
}