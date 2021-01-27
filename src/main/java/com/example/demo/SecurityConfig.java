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


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String USER_DATA = "SELECT"
			+ " userName,"
			+ " password,"
			+ " true"
			+ " FROM"
			+ " userData"
			+ " WHERE"
			+ " userName = ?"
			+ " AND userData.unavailableFlag IS NULL";

	private static final String ROLE_DATA = "SELECT"
			+ " userName,"
			+ " role"
			+ " FROM"
			+ " userData"
			+ " WHERE"
			+ " userName = ?"
			+ " AND userData.unavailableFlag IS NULL";

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/webjars/**", "css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		    .authorizeRequests()
		    	.antMatchers("/login").anonymous()
		    	.antMatchers("/mypage").authenticated()
		        .anyRequest().permitAll();

		http
			.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.defaultSuccessUrl("/mypage",true);

		http
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/login");
	}

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_DATA)
			.authoritiesByUsernameQuery(ROLE_DATA)
			.passwordEncoder(passwordEncoder());
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
