package com.spring.test;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogInterceptor {
	
	 @AfterThrowing("execution(public void com.spring.service.*.*(..))")
     public void show(){
    	 System.out.println("拦截了异常");
     }
}
