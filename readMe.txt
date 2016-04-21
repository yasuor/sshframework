@ManyToOne (默认加载方式为直接加载内存，即fetch =FetchType.EAGER)
       若是直接加载，则session关闭后仍可获取对象的属性值。当设置为懒加载时，session关闭后，就无法获取，报错。

@OneToMany (默认加载方式为lazy，即fetch =FetchType.LAZY)   

（处理json时，由于hibernate的懒加载机制（双向关联时），会出现问题，2种解决方法）
1.若使用springmvc的@ResponseBody,则加载方式需设为直接加载（不能为懒加载），
	同时为防止关联的两个对象（User，Exam）重复在数据库查询（会造成死循环）,
		需在多（成员对象的变量，exams）的一端添加@JsonIgnore(处理json转换)。
2.若使用fastjson将对象转换成json字符串时，需在exams前添加@JSONField(serialize=false)，
      	 并且在使用JSON.toJSONString(Object,SerializerFeature)设SerializerFeature.DisableCircularReferenceDetect
      	 	避免json字符串中使用"$ref"的引用。
    

常用设计模式：
  单例模式
   工厂模式
   观察者模式
  策略模式
  动态代理模式:(用到JDK中的Proxy类和InvocationHandler接口)
        UserDao ud =new UserDaoImpl();
		java.lang.reflect.InvocationHandler h =new MyProxyHandler(ud);
		UserDao up =(UserDao) java.lang.reflect.Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{UserDao.class}, h);
		up.sayHello();