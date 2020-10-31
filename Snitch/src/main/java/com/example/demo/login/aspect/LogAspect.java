package com.example.demo.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

////コントローラークラスとDAOクラスのメソッド実行の前後(Around)でログを出力 AOPのクラスなので＠Aspectを、DIコンテナへBean定義をするため@Componentをつける
@Aspect
@Component
public class LogAspect {

	//コントローラークラスのログ出力
	@Around("@within(org.springframework.stereotype.Controller)")
	/*
	 * 戻り値にはコントローラーなどのメソッドの戻り値がそのまま格納される
	 * ProceedingJoinPointは実行するメソッドの戻り値が取得でき、メソッド自体の実行も行える。
	 * proceed()はThrowableをスローする proceed()の戻り値はObject型
	 */
     public Object startLog(ProceedingJoinPoint jp) throws Throwable {

		//クラス名、メソッド名を取得し表示する
		System.out.println("メソッド開始:" + jp.getSignature());

		try {
			//AOP対象クラスのメソッドを直接実行
			Object result = jp.proceed();

			//クラス名、メソッド名を取得し表示する
			System.out.println("メソッド終了:" +jp.getSignature());

			return result;
		//エラー発生時 エラーメッセージを表示
		} catch (Exception e) {
			System.out.println("メソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	//daoクラスのログ出力
	@Around("execution(* *..*.*Dao.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		//クラス名、メソッド名を取得し表示する
		System.out.println("メソッド開始:"+ jp.getSignature());

		try {

            //AOP対象クラスのメソッドを直接実行
			Object result = jp.proceed();

			//クラス名、メソッド名を取得し表示する
			System.out.println("メソッド終了:" + jp.getSignature());

			return result;

		//エラー発生時 エラーメッセージを表示
		} catch (Exception e) {
			System.out.println("メソッド異常終了:" + jp.getSignature());
				e.printStackTrace();
				throw e;
		}
	}

}
