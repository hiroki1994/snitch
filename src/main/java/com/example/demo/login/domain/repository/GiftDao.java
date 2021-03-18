package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.login.domain.model.Gift;


public interface GiftDao  {

	public List<Gift> selectMany() throws DataAccessException;

	public Gift selectOne(int giftId) throws EmptyResultDataAccessException;

	public List<Gift> search(String keyword) throws DataAccessException;

	public int countByKeyword(String keyword) throws DataAccessException;

	public int countById(int giftId) throws DataAccessException;
}
