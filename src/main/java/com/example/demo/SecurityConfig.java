package com.example.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*セキュリティ用のクラス
 * セキュリティ用にBean定義を行うためConfigurationアノテーションを付与
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//SQLを実行するためにdataSourceをAutowired
	@Autowired
	private DataSource dataSource;


	/*パスワードの暗号化をするためPasswordEncoderを利用
	 *BcryptPasswordEncoderインスタンスを返す
	 *リポジトリークラスでも利用するためにBean定義
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//UserNameとpassword取得用のSQL文
	private static final String USER_DATA = "SELECT"
			+ " userName,"
			+ " password,"
			+ " true"
			+ " FROM"
			+ " userData"
			+ " WHERE"
			+ " userName = ?"
			+ " AND userData.unavailableFlag IS NULL";

	//権限取得用のSQL文
	private static final String ROLE_DATA = "SELECT"
			+ " userName,"
			+ " role"
			+ " FROM"
			+ " userData"
			+ " WHERE"
			+ " userName = ?"
			+ " AND userData.unavailableFlag IS NULL";

	/*
	 * configureメソッドをoverrideし引数のWebseCurityについて設定
	 * 静的コンテンツに対してはセキュリティを設定しないようにするため、configure(WebSecurity)をOverride
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {


		//静的リソースにはセキュリティを適用しないよう設定
		web.ignoring().antMatchers("/webjars/**", "css/**");
	}

	/*
	 * configureメソッドをoverrideし引数のHttpSecurityについて設定
	 * URLによって権限を設定するためにconfigure(HttpSecurity http)をOverride
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//「/mypage」配下は直リンクできないように設定
		http
		    .authorizeRequests()
		    	.antMatchers("/mypage").authenticated()
		        .anyRequest().permitAll(); //「mypage」配下以外は直リンク可能

		//ログイン設定
		http
			.formLogin()
				.loginProcessingUrl("/login") //ログイン処理をするURLの設定
				.loginPage("/login") //自作したログインページをリンク先に設定
				.failureUrl("/login") //ログイン失敗時は再度ログインページへ遷移
				.usernameParameter("userName") //userName入力エリアのパラメーター名指定
				.passwordParameter("password") //Password入力エリアのパラメーター名指定
				.defaultSuccessUrl("/mypage",true); //ログイン成功時マイページへ遷移


		//ログアウト設定
		http
			.logout()
				.logoutUrl("/logout") //リクエストとして「/logout」パスを取得したらログアウトを実行
				.invalidateHttpSession(true) //ログアウト時にHttpSessionを無効にする
				.logoutSuccessUrl("/login"); //ログアウト成功時ログイン画面へ遷移


	}

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	//ログイン処理時にユーザー情報をテーブルから取得
		auth.jdbcAuthentication()//dbcAuthenticationメソッドでデータベースからユーザ情報を読み取ることを指定
			.dataSource(dataSource) //利用するデータソースを指定 JDBC認証を使用していることを示している
			.usersByUsernameQuery(USER_DATA) //SQL文を引数に入れSpringに認証を行ってもらう
			.authoritiesByUsernameQuery(ROLE_DATA) //SQL文を引数に入れSpringに認証を行ってもらう
			.passwordEncoder(passwordEncoder()); //ログイン時にパスワード復号

	}

    public static void authWithHttpServletRequestLogin(HttpServletRequest request, String username, String password, HttpServletResponse response) throws IOException {

    	if(request.getUserPrincipal() != null) {
    		authWithHttpServletRequestLogout(request, response);
    		System.out.println("認証済みの状態だったからログアウトしたよ");
    	}

        try {
            request.login(username, password);
            System.out.println(username + "＆");
			System.out.println(password + "＆");
            String url = "/mypage";
    		response.sendRedirect(url);
        } catch (ServletException e) {
        	String url = "/login";
    		response.sendRedirect(url);
        }


    }

    public static void authWithHttpServletRequestLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            request.logout();
            System.out.println("ログアウト実行");
            String url = "/login";
    		response.sendRedirect(url);
        } catch (ServletException e) {
        	String url = "/login";
    		response.sendRedirect(url);
        }
    }
}
