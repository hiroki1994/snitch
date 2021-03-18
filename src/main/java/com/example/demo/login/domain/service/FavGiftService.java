package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	public boolean create(String userName, int giftId) {

		try {
			dao.create(userName, giftId);

			return true;

		} catch(DataIntegrityViolationException | EmptyResultDataAccessException e) {

			return false;
		}
	}

	public boolean delete(String userName, int giftId) {

		try {
			int suceededRowNumber = dao.delete(userName, giftId);

			System.out.println(suceededRowNumber);

			if(suceededRowNumber > 0) {
				return true;
			}

			return false;

		} catch (EmptyResultDataAccessException e) {

			return false;
		}

	}

}
