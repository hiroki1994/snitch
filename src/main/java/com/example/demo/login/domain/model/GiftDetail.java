package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class GiftDetail {
	private int giftId;
	private String guestName;
	private String giftName;
	private String price;
	private String image;
	private String description;
	private String shop;
	private String address;
	private String phone;
}
