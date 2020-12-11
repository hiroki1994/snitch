package com.example.demo.login.domain.model;

import lombok.Data;

//お気に入りテーブル「FavOmiyage」のカラムをフィールドに持つクラス
@Data
public class FavOmiyage {
	private String favId;
	private String userId;
	private int omiyaID;
	private String guest;
	private String name;
	private String price;
	private String image;
	private String description;
	private String shop;
	private String address;
	private String phone;
}
