package com.example.demo.login.domain.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;


//ユーザー新規登録フォームで使用するSignupForm
@Data
public class SignupForm {

	/*
	 * null 半角スペース 空文字の場合エラー発生(ValidGroup1に分類)
	 * 3字以上20字以内、英数字のみ(ValidGroup2に分類)
	 */
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 3, max = 20, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String userName;

	/*
	 * null 半角スペース 空文字の場合エラー発生(ValidGroup1に分類)
	 * メールアドレスの形式でないとエラー発生(ValidGroup2に分類)
	 */
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String mailAddress;

	/*
	 * null 半角スペース 空文字の場合エラー発生(ValidGroup1に分類)
	 *  3字以上20字以内(ValidGroup2に分類)
	 *  英数字のみ(ValidGroup2に分類)
	 */
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 3, max = 20, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;

}
