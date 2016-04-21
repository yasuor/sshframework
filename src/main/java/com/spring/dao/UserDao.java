package com.spring.dao;


import com.spring.model.User;

public interface UserDao {
	
	 public void save(User user);
     
	 public User getUserById(int id);
	 
	 public int deleteUser(int id);
	 
	 public void sayHello(String name);
}
