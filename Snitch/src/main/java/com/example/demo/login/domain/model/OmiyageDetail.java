package com.example.demo.login.domain.model;

import lombok.Data;

//お土産詳細画面表示の際に使用する「OmiyageDetail」クラス
@Data
public class OmiyageDetail {
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
