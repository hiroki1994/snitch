package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

//ユーザー新規登録用コントローラー
@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	//ユーザー登録画面へ遷移
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute SignupForm form, Model model) {

		return "signup/signup";
	}

	//ユーザー新規登録実行用メソッド
	@PostMapping("/signupUser") //「GroupOrder」で設定した順序でバリデーションを実行
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {

		//バインディングでエラー発生(バリデーションエラー含む)した場合、ユーザー登録画面へ遷移
		if (bindingResult.hasErrors()) {
			return postSignUp(form, model);

		}

		//フォームに入力した内容をコンソールへ表示
		System.out.println(form);

		//Userインスタンスの作成
		User user = new User();

		//登録フォームに入力した内容をUserクラスにセット
		user.setUserId(form.getUserId());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		//フォーム入力内容をセットした「User」を引数にサービスクラスへ処理を投げる
		boolean result = userService.insertOne(user);

		//登録作業の実行結果をコンソールに表示
		if (result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}

		//ログイン画面へ遷移
		return "redirect:/login";

	}

	//「SignUpController」で「DataAccessException」が発生した場合、500エラーページを表示
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		System.out.println("Signupだよー");
		//エラーメッセージをキー「message」に格納
		model.addAttribute("message", "入力されたログインIDは既に使用されている可能性があります。");


		return "error";
	}


}
