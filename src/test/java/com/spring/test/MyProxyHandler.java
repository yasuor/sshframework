package com.spring.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {
	private Object target;

	public MyProxyHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object o, Method m, Object[] args) throws Throwable {
		System.out.println("Transaction Start.....");
		m.invoke(target,args);
		System.out.println("Transaction Commit.....");
		return o;
	}

}
