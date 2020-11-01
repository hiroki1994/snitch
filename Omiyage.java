package com.example.demo.login.domain.model;

import lombok.Data;

//テーブル「omiyage」のカラムをフィールドに持つクラス
@Data
public class Omiyage {
	private int omiyaID;
	private String guest;
	private String name;
	private String price;
	private String image;
	private String description;
	private String shop;
	private String address;
	private String phone;
	private String keyword;
}
