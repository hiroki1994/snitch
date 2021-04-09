package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.favorite.FavGift;
import com.example.demo.domain.repository.FavoriteDao;

@Transactional
@Service
public class FavoriteService {

    @Autowired
    FavoriteDao dao;

    public List<FavGift> selectAll(String userName) {
	return dao.selectAll(userName);
    }

    public int count(String userName) {
	return dao.count(userName);
    }

    public boolean exist(String userName, int giftId) {

	try {
	    dao.exist(userName, giftId);
	    return true;
	} catch (EmptyResultDataAccessException e) {
	    return false;
	}
    }

    public int createOne(String userName, int giftId) {
	return dao.createOne(userName, giftId);
    }

    public int deleteOne(String userName, int giftId) {
	return dao.deleteOne(userName, giftId);
    }

    public int deleteMany(String userName) {
	return dao.deleteMany(userName);
    }
}