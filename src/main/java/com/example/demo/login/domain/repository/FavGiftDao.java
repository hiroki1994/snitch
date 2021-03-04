package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.FavGift;


public interface FavGiftDao  {

	public List<FavGift> selectAll(String userName) throws DataAccessException;

	public int count(String userName) throws DataAccessException;

	public int create(String userName, int giftId) throws DataAccessException;

	public int existFavId(String userName, int giftId) throws DataAccessException;

	public int delete(String userName, int giftId) throws DataAccessException;
}
