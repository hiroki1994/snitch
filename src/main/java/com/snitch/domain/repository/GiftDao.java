package com.snitch.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.snitch.domain.model.gift.Gift;

public interface GiftDao {

    public List<Gift> selectMany() throws DataAccessException;

    public Gift selectOne(int giftId) throws EmptyResultDataAccessException;

    public List<Gift> search(String keyword) throws DataAccessException;

    public int count(String keyword) throws DataAccessException;
}