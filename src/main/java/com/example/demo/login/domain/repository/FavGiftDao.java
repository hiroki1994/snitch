package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.FavGift;


//お気に入り登録用リポジトリークラスのインタフェース
public interface FavGiftDao  {

	//お気に入り一覧表示用　お気に入り登録済みのお土産を全件取得
	public List<FavGift> selectMany(String userName) throws DataAccessException;

	//お気に入り登録済みのお土産の件数取得
	public int count(String userName) throws DataAccessException;

	//お気に入り登録用 お気に入りテーブルに一件データを挿入
	public int insert(String userName, int giftId) throws DataAccessException;

	//お気に入り登録ID「favId」取得
	public int searchFavId(String userName, int giftId) throws DataAccessException;

	//お気に入り登録解除用　お気に入りテーブルから一件削除
	public int delete(String userName, int giftId) throws DataAccessException;
}
