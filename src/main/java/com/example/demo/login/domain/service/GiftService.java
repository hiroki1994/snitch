package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.repository.GiftDao;


/*テーブル「omiyage」操作用のサービスメソッド
 * トランザクション処理を利用
 */
@Transactional
@Service
public class GiftService {

	@Autowired
	GiftDao dao;

	//Home画面に表示するお土産を複数選出
	public List<Gift> selectMany() {

	    //リポジトリークラスに処理を投げる
		return dao.selectMany();
	}

	//テーブル「omiyage」より1件取得
	public Gift selectOne(int giftId) {
		return dao.selectOne(giftId);
	}

	//テーブル「omiyage」より該当のkeywordを含むお土産を取得
	public List<Gift> search(String keyword) {
		System.out.println(keyword+"2");

		//検索キーワード「keyword」を引数にリポジトリークラスに処理を投げる
		return dao.search(keyword);
	}

	//検索結果の件数取得
	public int count(String keyword) {

		//検索キーワード「keyword」を引数にリポジトリークラスに処理を投げる
		return dao.count(keyword);
	}

}
