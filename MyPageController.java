package com.example.demo.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.FavOmiyage;
import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.FavOmiyageService;
import com.example.demo.login.domain.service.UserService;


//マイページ操作用コントローラークラス
@Controller
public class MyPageController {

	@Autowired
	UserService userService;

	@Autowired
	FavOmiyageService favOmiyageService;

	//マイページ「mypgae.html」を表示
	@GetMapping("/mypage")
	public String getMypage() {

		return "mypage/mypage";
	}

	//退会用ページ「deleteUser.html」を表示
	@PostMapping("/mypage/deleteUser")
	public String getDelete() {

		return "mypage/deleteUser/deleteUser";
	}

	//退会メソッド
	@PostMapping("/deleteUser")
	public String postDeleteUser(HttpServletRequest httpServletRequest) {

		//認証済みのユーザーのIDを取得
		String userId = httpServletRequest.getRemoteUser();

		//ユーザーID取得確認用
		System.out.println(userId +"の登録情報を削除します");

		//ログイン中のユーザーIDを引数にサービスへ処理を投げる
	    boolean result = userService.deleteOne(userId);


	    //ユーザーデータ削除動作確認用
	    if(result == true) {
	    	System.out.println("ユーザー情報削除成功");
	    } else {
	    	System.out.println("ユーザー情報削除失敗");
	    }

	    //ログイン中のユーザーIDを引数にサービスへ処理を投げる
	    boolean result2 = favOmiyageService.deleteAll(userId);

	  //お気に入りデータ削除動作確認用
	    if(result2 == true) {
	    	System.out.println("お気に入りデータ削除成功");
	    } else {
	    	System.out.println("お気に入りデータ削除失敗");
	    }

	    /*「logout.html」へ遷移
	     * ユーザー退会時に自動で認証を取り消すことができず、ログアウト用のページに一瞬遷移させ認証を取り消すように設計
	     */
		return "logout/logout";
	}

	//ユーザー登録情報更新ページ表示用メソッド
	@PostMapping("/mypage/updateUser")
	public String PostUserUpdatePage(@ModelAttribute SignupForm form, Model model, HttpServletRequest httpServletRequest) {

		//認証済みのユーザーのIDを取得
		String userId = httpServletRequest.getRemoteUser();

		//ユーザーID取得確認用
		System.out.println(userId+"の登録情報を更新します");

		/*ユーザーIDが取得できたとき、ユーザーIDからユーザーデータを検索
		 *Userクラスに取得した値をセットする
		 */
		if(userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			form.setUserId(user.getUserId());
			form.setMailAddress(user.getMailAddress());

			//画面表示させるためにmodelオブジェクト「signupForm」に取得結果を格納
			model.addAttribute("signupForm", form);
		}

		return "mypage/updateUser/updateUser";
    }




	//ユーザー登録情報更新実行用メソッド
	@PostMapping("/updateUserInfo") //「GroupOrder」で設定した順序でバリデーションを実行
	public String postUserUpdate(@ModelAttribute @Validated(GroupOrder.class)SignupForm form, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

		//バインディングでエラー発生(バリデーションエラー含む)した場合、登録情報更新ページへ遷移
		if (bindingResult.hasErrors()) {
			return PostUserUpdatePage(form, model, httpServletRequest);

		}

		//更新対象のユーザーを検索するために認証済みのユーザーIDを取得
		String userId_LoggedIn = httpServletRequest.getRemoteUser();

		//ユーザーID取得確認用
		System.out.println("ログインしているのは"+ userId_LoggedIn);


		//Userインスタンスの生成
		User user = new User();

		//更新後のデータも含め、SignupFormに格納されているデータをUserクラスにセットする
		user.setUserId(form.getUserId());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		//ログインIDと更新後の内容が格納されたUserを引数に更新
		boolean result = userService.updateOne(user, userId_LoggedIn);

		//ユーザーデータ更新動作確認用
		if(result == true) {
		   System.out.println("更新成功");

		} else {
			System.out.println("更新失敗");
		}

		//更新後、再認証をするためにログアウトさせる
		return "logout/logout";
	}

	//お気に入り画面表示用
	@PostMapping("/mypage/favorite")
	public String getFavorite(Model model, HttpServletRequest httpServletRequest) {

		//更新対象のユーザーを検索するために認証済みのユーザーのIDを取得
		String userId = httpServletRequest.getRemoteUser();

		//ユーザーID取得確認用
		System.out.println("ログインしているのは"+userId);

		//登録済みのお土産を全件取得、リストに格納
		List<FavOmiyage> favOmiyageList = favOmiyageService.selectMany(userId);

		//画面表示させるためにmodelオブジェクト「favOmiyageList」に取得結果を格納
		model.addAttribute("favOmiyageList", favOmiyageList);

		//お気に入り済みのお土産の件数を取得
		int count = favOmiyageService.count(userId);

		//画面表示させるためにmodelオブジェクト「favOmiyageListCount」に取得結果を格納
		model.addAttribute("favOmiyageListCount", count);

		return "mypage/favorite/favorite";
	}
}
