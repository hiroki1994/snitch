package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;


@Transactional
@Service
public class FavGiftService {

	@Autowired
	FavGiftDao dao;

	public List<FavGift> selectMany(String userName) {

		return dao.selectMany(userName);
	}

	public int count(String userName) {

		return dao.count(userName);
	}

	public int searchFavId(String userName, int giftId) {

		return dao.searchFavId(userName, giftId);
	}

	public boolean create(String userName, int giftId) {

		int favGiftRowNumber = dao.create(userName, giftId);

		boolean result = false;

		if (favGiftRowNumber > 0) {
			result = true;
		}

		return result;

	}

	public boolean delete(String userId, int giftId) {

		int favGiftRowNumber = dao.delete(userId, giftId);

		boolean result = false;

		if (favGiftRowNumber > 0) {
			result = true;
		}

		return result;

	}

}
