package com.spring.service;

import java.util.List;
import java.util.Set;

import com.spring.model.Exam;
import com.spring.model.ExamInfo;
import com.spring.model.User;

public interface UserService {
	public void addUser(User user);
	
	public int addUsers(List<User> users);//在一个事务中，保存多个User对象。

	public void addExam(Exam exam);

	public Exam getExamById(int id);

	public User getUserById(int id);

	public Set<Exam> getExamsByUserId(int id);

	public int deleteUserById(int id);
	
	public int addExam(int id,Exam exam);
	
	public int updateExamById(int id,Exam exam);
	
	public List<Exam> queryExams(ExamInfo examInfo);
}
