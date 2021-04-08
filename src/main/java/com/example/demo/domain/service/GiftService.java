package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.gift.Gift;
import com.example.demo.domain.repository.GiftDao;

@Transactional
@Service
public class GiftService {

    @Autowired
    GiftDao dao;

    public List<Gift> selectMany() {
	return dao.selectMany();
    }

    public Gift selectOne(int giftId) {
	return dao.selectOne(giftId);
    }

    public List<Gift> search(String keyword) {
	return dao.search(keyword);
    }

    public int count(String keyword) {
	return dao.count(keyword);
    }
}