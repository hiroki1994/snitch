package com.snitch.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snitch.domain.model.user.User;
import com.snitch.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

    @Autowired
    UserDao dao;

    public int createOne(User user) {
	return dao.createOne(user);
    }

    public User selectOne(String userName) {
	return dao.selectOne(userName);
    }

    public int updateOne(User user, String userName_LoggedIn) {
	return dao.updateOne(user, userName_LoggedIn);
    }

    public int deleteOne(String userName) {
	return dao.deleteOne(userName);
    }

    public int exist(String userName) {
	return dao.exist(userName);
    }
}