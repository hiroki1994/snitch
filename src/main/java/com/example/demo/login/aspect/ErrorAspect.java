package com.example.demo.login.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

//「DataAccessException」発生時にログを出力 AOPのクラスなので＠Aspectを、DIコンテナへBean定義をするため@Componentをつける
@Aspect
@Component
public class ErrorAspect {

	/*エラー発生時、共通処理(ログ出力)を行う。 「~Controller」「~Service」「~Repository」全パッケージ全クラスを対象とする
	 * 発生した例外は変数「ex」に格納される
	 */
	@AfterThrowing(value="execution(* *..*.*..*(..))" + " &&(bean(*Controller) || bean(*Service) || bean(*Repository))" , throwing="ex")
	public void throwingNull(DataAccessException ex)
	{
     System.out.println("=========================================");
     System.out.println("DataAccessExceptionが発生しました。:" + ex);
     System.out.println("=========================================");
	}

	@AfterThrowing(value="execution(* *..*.*..*(..))" + " &&(bean(*Controller) || bean(*Service) || bean(*Repository))" , throwing="ex")
	public void throwingNull(Exception ex)
	{
     System.out.println("=========================================");
     System.out.println("Exceptionが発生しました。:" + ex);
     System.out.println("=========================================");
	}

	@AfterThrowing(value="execution(* *..*.*..*(..))" + " &&(bean(*Controller) || bean(*Service) || bean(*Repository))" , throwing="ex")
	public void throwingNull(EmptyResultDataAccessException ex)
	{
     System.out.println("=========================================");
     System.out.println("EmptyResultDataAccessExceptionが発生しました。:" + ex);
     System.out.println("=========================================");
	}

	@AfterThrowing(value="execution(* *..*.*..*(..))" + " &&(bean(*Controller) || bean(*Service) || bean(*Repository))" , throwing="ex")
	public void throwingNull(ConversionFailedException ex)
	{
     System.out.println("=========================================");
     System.out.println("ConversionFailedExceptionが発生しました。:" + ex);
     System.out.println("=========================================");
	}
}