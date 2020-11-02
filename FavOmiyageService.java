package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavOmiyage;
import com.example.demo.login.domain.repository.FavOmiyageDao;

/*テーブル「FavOmiyage」操作用のサービスメソッド
 * トランザクション処理を利用
 */
@Transactional
@Service
public class FavOmiyageService {

	@Autowired
	FavOmiyageDao dao;

	//お気に入り済みのお土産全件取得
	public List<FavOmiyage> selectMany(String userId) {

		//認証済みの「userId」を引数にリポジトリークラスへ処理を投げる
		return dao.selectMany(userId);
	}

	//お気に入り登録済みのお土産の件数取得
	public int count(String userId) {

		//認証済みの「userId」を引数にリポジトリークラスへ処理を投げる
		return dao.count(userId);
	}

	//お気に入り登録時に発行されるIDの有無を確認
	public String searchFavId(String favId) {

		//「favId」を引数にリポジトリークラスへ処理を投げる
		return dao.searchFavId(favId);
	}


	//お気に入り登録用サービスメソッド
	public boolean insert(int favOmiyaID, String userId) {

		//認証済みの「userId」と「favOmiyaID」(OmiyaID)を引数にリポジトリークラスへ処理を投げる
		int rowNumber = dao.insert(favOmiyaID, userId);

		boolean result = false;

		//更新されたテーブルの行数でメソッドの動作判定
		if (rowNumber > 0) {
			result = true;
		}

		return result;

	}

	//お気に入り登録解除用サービスメソッド
	public boolean delete(String favId) {

		//「favId」を引数にリポジトリークラスへ処理を投げる
		int rowNumber = dao.delete(favId);

		boolean result = false;

		//更新されたテーブルの行数でメソッドの動作判定
		if (rowNumber > 0) {
			result = true;
		}

		return result;

	}

	//退会したユーザーIDに紐づいたお気に入り情報の全件削除
	public boolean deleteAll(String userId) {

		int rowNumber = dao.deleteAll(userId);

		boolean result = false;

		//更新されたテーブルの行数でメソッドの動作判定
		if (rowNumber > 0) {
			result = true;
	}

	return result;

	}
}
