package com.example.demo.domain.model.gift;

import lombok.Data;

@Data
public class Gift {
	private int giftId;
	private int recommenderId;
	private String recommenderName;
	private String giftName;
	private String price;
	private String image;
	private String description;
	private String shop;
	private String address;
	private String phone;
	private boolean isEnabled;
}
