package com.snitch.domain.model.favorite;

import lombok.Data;

@Data
public class Favorite {
    private int favoriteId;
    private int userId;
    private int giftId;
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