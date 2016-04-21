package com.spring.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.spring.dao.UserDao;
import com.spring.dao.impl.UserDaoImpl;

public class Test{
     
	@org.junit.Test
	public void getSystemDir(){
		UserDao ud =new UserDaoImpl();
		InvocationHandler h =new MyProxyHandler(ud);
		UserDao up =(UserDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserDao.class}, h);
		System.out.println(up.getClass());// class com.sun.proxy.$Proxy2(代理类)
		up.sayHello("May");
		//当前工程路径
		System.out.println(System.getProperty("user.dir"));//   E:\workspace\mvn-web
		System.out.println(System.getProperty("java.home"));//  C:\Program Files\Java\jre1.8.0_65
		System.out.println(System.getProperty("user.home"));//  C:\Users\Administrator
	}
	
	
}
