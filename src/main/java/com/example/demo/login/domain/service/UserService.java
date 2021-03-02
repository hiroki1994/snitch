package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;


@Transactional
@Service
public class UserService {

	@Autowired
	UserDao dao;

	public boolean insertOne(User user) {

		try {
			dao.insertOne(user);

			return true;
		} catch(DataAccessException e) {

			return false;
		}
	}

	public User selectOne(String userName) {

		return dao.selectOne(userName);

	}

	public boolean updateOne(User user, String userName_LoggedIn) {

		try {
			dao.updateOne(user, userName_LoggedIn);

			return true;
		} catch(DataAccessException e) {

			return false;
		}
	}

	public boolean deleteOne(String userName) {

		try {
			dao.deleteOne(userName);

			return true;
		}  catch(DataAccessException e) {

			return false;
		}
	}



	public int exist(String userName) {

		int userNameExist = dao.exist(userName);

		return userNameExist;
	}
}
