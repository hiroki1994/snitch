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

		boolean result = false;

		try {

			dao.insertOne(user);

			result = true;

		} catch(DataAccessException e) {

			return result;

		}

		return result;
	}

	public User selectOne(String userName) {

		return dao.selectOne(userName);

	}

	public boolean updateOne(User user, String userName_LoggedIn) {


		int rowNumber = dao.updateOne(user, userName_LoggedIn);

		boolean result = false;

		if (rowNumber > 0) {

			result = true;
		}

		return result;
	}

	public boolean deleteOne(String userName) {

		int rowNumber = dao.deleteOne(userName);

		boolean result = false;

		if (rowNumber > 0) {

			result = true;
		}

		return result;
	}



	public int exist(String userName) {

		int userNameExist = dao.exist(userName);

		return userNameExist;
	}
}
