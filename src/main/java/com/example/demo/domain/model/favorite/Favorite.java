package com.example.demo.domain.model.favorite;

import lombok.Data;

@Data
public class Favorite {
    private int favId;
    private int userId;
    private int giftId;
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