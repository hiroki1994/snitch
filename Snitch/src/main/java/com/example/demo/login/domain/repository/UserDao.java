package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

//ユーザーリポジトリークラスのインタフェース
public interface UserDao {

	//Userテーブルにデータを1件insert
	public int insertOne(User user) throws DataAccessException;

	 //Userテーブルを1件更新
	public int updateOne(User user, String userId_LoggedIn) throws DataAccessException;

	//Userデーブルのデータを1件取得
	public User selectOne(String userId) throws DataAccessException;

	//Userテーブルのデータを1件削除
	public int deleteOne(String userId) throws DataAccessException;
}
