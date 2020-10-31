package com.example.demo.login.domain.model;


import javax.validation.constraints.NotBlank;

import lombok.Data;

//検索フォームで使用する「SearchForm」クラス　
@Data
public class SearchForm {

	//null 半角スペース 空文字の場合エラー発生
	@NotBlank
	private String keyword;

}