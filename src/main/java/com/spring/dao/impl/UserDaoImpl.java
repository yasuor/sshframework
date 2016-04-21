package com.spring.dao.impl;


import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.spring.dao.UserDao;
import com.spring.model.Exam;
import com.spring.model.User;

@Component
public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
    
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	@Override
	public void save(User user){
		Session s=sessionFactory.getCurrentSession();
		s.save(user);
	}

	@Override
	public User getUserById(int id) {
		Session s=sessionFactory.getCurrentSession();
		User u =(User) s.get(User.class, id);
		return u;
	}

	@Override
	public int deleteUser(int id) {
		Session s=sessionFactory.getCurrentSession();
		User user =(User) s.get(User.class, id);
		if(user!=null){
			s.delete(user);
			return 1;
		}
		return -1;
	}

	@Override
	public void sayHello(String name) {
		System.out.println("Hello!"+name+"!");
	}

	
}
