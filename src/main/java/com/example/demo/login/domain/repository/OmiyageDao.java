package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.Omiyage;


//お土産用リポジトリークラスのインタフェース
public interface OmiyageDao  {

	//お土産テーブルからデータを複数件取得
	public List<Omiyage> selectMany() throws DataAccessException;

	///お土産テーブルからデータを一件取得 詳細画面表示用
	public Omiyage selectOne(int omiyaID) throws DataAccessException;

	//お土産テーブルから検索キーワード「keyword」に一致するデータを検索
	public List<Omiyage> search(String keyword) throws DataAccessException;

	//検索結果の件数取得
	public int count(String keyword) throws DataAccessException;
}
