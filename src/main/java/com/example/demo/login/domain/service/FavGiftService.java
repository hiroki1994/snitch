package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;


@Transactional
@Service
public class FavGiftService {

	@Autowired
	FavGiftDao dao;

	public List<FavGift> selectAll(String userName) {

		return dao.selectAll(userName);

	}

	public int count(String userName) {

		return dao.count(userName);

	}

	public boolean existFavId(String userName, int giftId) {

		try {
			dao.existFavId(userName, giftId);

			return true;

		} catch(EmptyResultDataAccessException e) {

			return false;

		}
	}

	public int create(String userName, int giftId) {

		return dao.create(userName, giftId);

	}

	public int delete(String userName, int giftId) {

		return dao.delete(userName, giftId);

	}

	public int deleteMany(String userName) {

		return dao.deleteMany(userName);
	}
}