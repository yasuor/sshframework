package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ExamDao;
import com.spring.dao.UserDao;
import com.spring.model.Exam;
import com.spring.model.ExamInfo;
import com.spring.model.User;
@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ExamDao examDao;
	

	@Override
	@Transactional
	public void addUser(User user) {
		
		userDao.save(user);
		
	}


	@Override
	@Transactional
	public void addExam(Exam exam) {
		
		examDao.add(exam);
	}


	@Override
	@Transactional
	public Exam getExamById(int id) {
		
		return examDao.getExamById(id);
	}


	@Override
	@Transactional
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}


	@Override
	@Transactional
	public Set<Exam> getExamsByUserId(int id) {
		return examDao.getExamsByUserId(id);
	}


	@Override
	@Transactional
	public int deleteUserById(int id) {
			return userDao.deleteUser(id);
	}


	@Override
	@Transactional
	public int addExam(int id, Exam exam) {
		return examDao.addExam(id, exam);
	}


	@Override
	@Transactional
	public int updateExamById(int id, Exam exam) {
		return examDao.updateExamById(id, exam);
	}


	@Override
	@Transactional
	public int addUsers(List<User> users) {
		for(User u:users){
			userDao.save(u);
		}
		return users.size();
	}


	@Override
	@Transactional
	public List<Exam> queryExams(ExamInfo examInfo) {
		/*Map<String, Object> map= new HashMap<String,Object>();
		map.put("examType", examInfo.getExamType());
		map.put("workType", examInfo.getWorkType());
		map.put("score", examInfo.getScore());
		map.put("score2", examInfo.getScore2());
		map.put("testTime", examInfo.getTestTime());
		map.put("testTime2", examInfo.getTestTime2());
		map.put("user", examInfo.getUser());*/
		return examDao.queryExams(examInfo);
	}

}
