package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
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
			+ " isEnabled AS enabled"
			+ " FROM"
			+ " users"
			+ " WHERE"
			+ " userName = ?";

	private static final String ROLE_DATA = "SELECT"
			+ " userName,"
			+ " role"
			+ " FROM"
			+ " users"
			+ " WHERE"
			+ " userName = ?"
			+ " AND users.isEnabled IS true";

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/webjars/**", "css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		    .authorizeRequests()
		    	.antMatchers("/users/session/login").anonymous()
		    	.antMatchers(HttpMethod.GET, "/users").authenticated()
		    	.antMatchers(HttpMethod.PUT, "/users").authenticated()
		    	.antMatchers(HttpMethod.DELETE, "/users").authenticated()
		    	.antMatchers(HttpMethod.GET, "/favorites").authenticated()
		    	.antMatchers(HttpMethod.POST, "/favorites").authenticated()
		    	.antMatchers(HttpMethod.DELETE, "/favorites").authenticated()
		        .anyRequest().permitAll();

		http
			.formLogin()
				.loginProcessingUrl("/users/session/login")
				.loginPage("/users/session/login")
				.failureUrl("/users/session/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.defaultSuccessUrl("/users/mypage",true);

		http
			.logout()
				.logoutUrl("/users/session/logout")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/users/session/login");
	}

    @Override
	protected void configure(AuthenticationManagerBuilder auth) {

		try {
		    auth.jdbcAuthentication()
		    	.dataSource(dataSource)
		    	.usersByUsernameQuery(USER_DATA)
		    	.authoritiesByUsernameQuery(ROLE_DATA)
		    	.passwordEncoder(passwordEncoder());
		} catch (Exception e) {
		    // TODO 自動生成された catch ブロック
		    e.printStackTrace();
		}
	}

    public static void autoLogin(HttpServletRequest request, String username, String password, HttpServletResponse response) throws IOException {

    	if(request.getUserPrincipal() != null) {
    		SecurityContextHolder.clearContext();
    	}


     	try {
            request.login(username, password);

            String urlMyPage = "/users/mypage";

    		response.sendRedirect(urlMyPage);
        } catch (ServletException e) {
        	String urlLogin = "/users/session/login";

    		response.sendRedirect(urlLogin);
        }
    }

    public static void autoLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.logout();

            String urlLogin = "/users/session/login";

    		response.sendRedirect(urlLogin);
        } catch (ServletException e) {
        	String urlLogin = "/users/session/login";

    		response.sendRedirect(urlLogin);
        }
    }
}
