package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Gift;


public interface GiftDao  {

	public List<Gift> selectMany() throws DataAccessException;

	public Gift selectOne(int giftId) throws DataAccessException;

	public List<Gift> search(String keyword) throws DataAccessException;

	public int count(String keyword) throws DataAccessException;
}
