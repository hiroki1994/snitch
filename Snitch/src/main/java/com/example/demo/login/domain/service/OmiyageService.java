package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.domain.model.Omiyage;
import com.example.demo.login.domain.repository.OmiyageDao;


/*テーブル「omiyage」操作用のサービスメソッド
 * トランザクション処理を利用
 */
@Transactional
@Service
public class OmiyageService {

	@Autowired
	OmiyageDao dao;

	//Home画面に表示するお土産を複数選出
	public List<Omiyage> selectMany() {

	    //リポジトリークラスに処理を投げる
		return dao.selectMany();
	}

	//テーブル「omiyage」より1件取得
	public Omiyage selectOne(int omiyaID) {
		return dao.selectOne(omiyaID);
	}

	//テーブル「omiyage」より該当のkeywordを含むお土産を取得
	public List<Omiyage> search(String keyword) {
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
