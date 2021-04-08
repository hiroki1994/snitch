package com.example.demo.domain.model.gift;

import lombok.Data;

@Data
public class Gift {
    private int giftId;
    private int guestId;
    private String guestName;
    private String giftName;
    private String price;
    private String image;
    private String description;
    private String shop;
    private String address;
    private String phone;
    private int unavailableFlag;
}