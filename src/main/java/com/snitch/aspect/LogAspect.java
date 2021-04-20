package com.snitch.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@within(org.springframework.stereotype.Controller)")
    public Object controllerLog(ProceedingJoinPoint jp) throws Throwable {

	System.out.println("メソッド開始:" + jp.getSignature());

	try {
	    Object result = jp.proceed();
	    System.out.println("メソッド終了:" + jp.getSignature());
	    return result;
	} catch (Exception e) {
	    System.out.println("メソッド異常終了:" + jp.getSignature());
	    e.printStackTrace();
	    throw e;
	}
    }

    @Around("@within(org.springframework.stereotype.Service)")
    public Object serviceLog(ProceedingJoinPoint jp) throws Throwable {

	System.out.println("メソッド開始:" + jp.getSignature());

	try {
	    Object result = jp.proceed();
	    System.out.println("メソッド終了:" + jp.getSignature());
	    return result;
	} catch (Exception e) {
	    System.out.println("メソッド異常終了:" + jp.getSignature());
	    e.printStackTrace();
	    throw e;
	}
    }

    @Around("execution(* *..*.*Dao.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {

	System.out.println("メソッド開始:" + jp.getSignature());

	try {
	    Object result = jp.proceed();
	    System.out.println("メソッド終了:" + jp.getSignature());
	    return result;
	} catch (Exception e) {
	    System.out.println("メソッド異常終了:" + jp.getSignature());
	    e.printStackTrace();
	    throw e;
	}
    }
}