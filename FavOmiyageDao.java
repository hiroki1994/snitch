package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.FavOmiyage;


//お気に入り登録用リポジトリークラスのインタフェース
public interface FavOmiyageDao  {

	//お気に入り一覧表示用　お気に入り登録済みのお土産を全件取得
	public List<FavOmiyage> selectMany(String userId) throws DataAccessException;

	//お気に入り登録済みのお土産の件数取得
	public int count(String userId) throws DataAccessException;

	//お気に入り登録用 お気に入りテーブルに一件データを挿入
	public int insert(int favOmiyaID, String userId) throws DataAccessException;

	//お気に入り登録ID「favId」取得
	public String searchFavId(String favId) throws DataAccessException;

	//お気に入り登録解除用　お気に入りテーブルから一件削除
	public int delete(String favId) throws DataAccessException;

	//退会したユーザーIDに紐づいたお気に入り情報の全件削除
	public int deleteAll(String userId)throws DataAccessException;
}
