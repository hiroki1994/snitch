package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.repository.FavGiftDao;

/*テーブル「FavOmiyage」操作用のサービスメソッド
 * トランザクション処理を利用
 */
@Transactional
@Service
public class FavGiftService {

	@Autowired
	FavGiftDao dao;

	//お気に入り済みのお土産全件取得
	public List<FavGift> selectMany(String userName) {

		//認証済みの「userId」を引数にリポジトリークラスへ処理を投げる
		return dao.selectMany(userName);
	}

	//お気に入り登録済みのお土産の件数取得
	public int count(String userName) {

		//認証済みの「userId」を引数にリポジトリークラスへ処理を投げる
		return dao.count(userName);
	}

	//お気に入り登録時に発行されるIDの有無を確認
	public int searchFavId(String userName, int giftId) {

		//「favId」を引数にリポジトリークラスへ処理を投げる
		return dao.searchFavId(userName, giftId);
	}


	//お気に入り登録用サービスメソッドここどうなるかなぁ
	public boolean insert(String userName, int giftId) {

		//認証済みの「userId」と「favOmiyaID」(OmiyaID)を引数にリポジトリークラスへ処理を投げる
		int rowNumber = dao.insert(userName, giftId);

		boolean result = false;

		//更新されたテーブルの行数でメソッドの動作判定
		if (rowNumber > 0) {
			result = true;
		}

		return result;

	}

	//お気に入り登録解除用サービスメソッド
	public boolean delete(String userId, int giftId) {

		//「favId」を引数にリポジトリークラスへ処理を投げる
		int rowNumber = dao.delete(userId, giftId);

		boolean result = false;

		//更新されたテーブルの行数でメソッドの動作判定
		if (rowNumber > 0) {
			result = true;
		}

		return result;

	}

}
