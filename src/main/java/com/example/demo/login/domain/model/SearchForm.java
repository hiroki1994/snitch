package com.example.demo.login.domain.model;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SearchForm {

	@NotBlank
	private String keyword;

}