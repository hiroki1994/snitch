package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class User {
	private String userId;
	private String userName;
	private String mailAddress;
	private String password;
	private String role;
	private int unavailableFlag;
}
