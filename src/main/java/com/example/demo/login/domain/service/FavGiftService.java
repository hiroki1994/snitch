package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
		boolean result = false;

		try {

			dao.existFavId(userName, giftId);

			result = true;

			return result;

		} catch(DataAccessException e) {

			return result;

		}
	}

	public boolean create(String userName, int giftId) {

		boolean result = false;

		try {

			dao.create(userName, giftId);

			result = true;

		} catch(DataIntegrityViolationException e) {

			result = false;

		}

		return result;
	}

	public boolean delete(String userName, int giftId) {

		boolean result = false;


		int suceededRowNumber = dao.delete(userName, giftId);

		System.out.println(suceededRowNumber);

		if(suceededRowNumber > 0) {

			result = true;

			return result;
		}

		return result;
	}

}
