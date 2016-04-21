package com.spring.model;

import java.util.Date;
/*
 * 用于查询时，参数封装
 */
public class ExamInfo {
	private int id;
	private int examType;// 试卷类型
	private int workType;// 工种
	private Date testTime;// 考试时间
	private Date testTime2;// 考试时间
	private int score;// 分数
	private int score2;// 分数
	private User user;// 答题人信息
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamType() {
		return examType;
	}

	public void setExamType(int examType) {
		this.examType = examType;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Date getTestTime2() {
		return testTime2;
	}

	public void setTestTime2(Date testTime2) {
		this.testTime2 = testTime2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
