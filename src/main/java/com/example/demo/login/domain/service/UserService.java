package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Transactional
@Service
public class UserService {

	@Autowired
	UserDao dao;

	public int create(User user) {

		return dao.create(user);
	}

	public User select(String userName) {

		return dao.select(userName);
	}

	public int update(User user, String userName_LoggedIn) {

		return dao.update(user, userName_LoggedIn);
	}

	public int delete(String userName) {

		return dao.delete(userName);
	}

	public int exist(String userName) {

		return dao.exist(userName);
	}
}
