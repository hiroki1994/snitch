package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;


//ユーザーテーブル「userData」操作用サービスクラス
//トランザクション処理を利用
@Transactional
@Service
public class UserService {

	@Autowired
	UserDao dao;

	//ユーザー新規登録用
	public boolean insertOne(User user) {


		//登録画面で入力された内容を格納した「User」を引数にリポジトリークラスに処理を投げる
		int rowNumber = dao.insertOne(user);

		boolean result = false;

		//データ登録が完了した場合、行数がリターンされるので、1行以上であれば成功とする
		if (rowNumber > 0) {
			result = true;
		}
		return result;
	}


	//ユーザーデータ1件取得
	public User selectOne(String userName) {


		//認証済みの「userName」を引数にリポジトリークラスに処理を投げる
		return dao.selectOne(userName);

	}

	//ユーザーデータ更新
	public boolean updateOne(User user, String userName_LoggedIn) {



		 //登録情報更新画面で入力された内容を格納した「user」と認証済みのuserName(userName_LoggedIn)を引数にリポジトリークラスに処理を投げる
		int rowNumber = dao.updateOne(user, userName_LoggedIn);

		boolean result = false;

		//データ更新が完了した場合、行数がリターンされるので、1行以上であれば成功とする
		if (rowNumber > 0) {

			result = true;
		}

		return result;
	}

	//ユーザーデータ削除
	public boolean deleteOne(String userName) {


		 //登録情報更新画面で入力された内容を格納した「user」と認証済みのuserName(userName_LoggedIn)を引数にリポジトリークラスに処理を投げる
		int rowNumber = dao.deleteOne(userName);

		boolean result = false;

		//データ削除が完了した場合、行数がリターンされるので、1行以上であれば成功とする
		if (rowNumber > 0) {

			result = true;
		}

		return result;
	}
}
