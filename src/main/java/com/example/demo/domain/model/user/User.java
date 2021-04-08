package com.example.demo.domain.model.user;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String mailAddress;
    private String password;
    private String role;
    private int unavailableFlag;
}