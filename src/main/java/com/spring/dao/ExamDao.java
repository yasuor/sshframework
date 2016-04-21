package com.spring.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spring.model.Exam;
import com.spring.model.ExamInfo;

public interface ExamDao {
	public void add(Exam exam);

	public Exam getExamById(int id);

	public Set<Exam> getExamsByUserId(int id);
	
	public int addExam(int id,Exam exam);
	
	public int updateExamById(int id,Exam exam);
	
	public List<Exam> queryExams(ExamInfo examInfo);
}
