package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * 下記の3つの機能を設定したのと同様の意味を持つ Spring Bootの起動クラスとして必要な機能が自動で組み込まれる
 * @EnableAutoConfiguration(Spring Bootの自動構成メカニズム)
 * @ComponentScan
 * @Configuration
 * プロジェクト実行時にSpringが起動します
 * warをWebコンテナに配置してアプリケーションが起動できるようにSpringBootServletInitializerを継承しないと
 */
@SpringBootApplication
public class SnitchApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		//SpringBootアプリケーションを起動する
		SpringApplication.run(SnitchApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		//「SnitchApplication.class」アプリケーションの構成クラスとして登録
		return builder.sources(SnitchApplication.class);
	}

}
