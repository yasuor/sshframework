package com.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class User {

	
	private int id;
	private String username;
	private String password;
	private int permission;
	private Set<Exam> exams =new HashSet<Exam>();
    @Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
    //OneToMany 默认加载方式为lazy,cascade=all(关联增删改)
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	@JSONField(serialize=false)
	public Set<Exam> getExams() {
		return exams;
	}

	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}
    
	
    
	
}
