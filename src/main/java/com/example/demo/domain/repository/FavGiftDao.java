package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.domain.model.favorite.FavGift;


public interface FavGiftDao  {

	public List<FavGift> selectAll(String userName) throws EmptyResultDataAccessException;

	public int count(String userName) throws EmptyResultDataAccessException;

	public int create(String userName, int giftId) throws DataIntegrityViolationException, EmptyResultDataAccessException;

	public int existFavId(String userName, int giftId) throws EmptyResultDataAccessException;

	public int delete(String userName, int giftId) throws EmptyResultDataAccessException;

	public int deleteMany(String userName) throws DataAccessException;
}