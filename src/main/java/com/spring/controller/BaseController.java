package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mysql.jdbc.MysqlErrorNumbers;
import com.spring.model.Exam;
import com.spring.model.ExamInfo;
import com.spring.model.User;
import com.spring.service.UserService;

@Controller
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add_exam", method = RequestMethod.GET)
	public String toAddExam() {
		return "add_exam";
	}

	@RequestMapping(value = "/add_exam", method = RequestMethod.POST)
	public String addExam(User user, Exam exam) {
		exam.setTestTime(new Date());
		userService.addExam(user.getId(), exam);
		return "redirect:/base/success";
	}

	@RequestMapping("/get_user")
	@ResponseBody
	public User getUser(User user) {
		System.out.println(userService.getClass());
		User user1 = userService.getUserById(user.getId());
		return user1;
	}

	@RequestMapping("/get_exams")
	@ResponseBody
	public Set<Exam> getExamsByUseId(User user) {
		Set<Exam> exams = userService.getExamsByUserId(user.getId());
		return exams;
	}

	@RequestMapping(value = "/query_exams", method = RequestMethod.GET)
	public String toQueryExams() {
		return "query_exams";
	}

	@RequestMapping(value = "/query_exams", method = RequestMethod.POST)
	public void queryExams(ExamInfo examInfo, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Exam> exams = userService.queryExams(examInfo);
		if (exams.size() == 0) {
			out.println("很抱歉，没有相关记录！");
		}

		/*
		 * SerializerFeature.DisableCircularReferenceDetect
		 * 避免json字符串中使用"$ref"的引用
		 */
		String jsonStr = JSON.toJSONString(exams, SerializerFeature.DisableCircularReferenceDetect);
		out.println(jsonStr);
	}

	@RequestMapping("/update_exam")
	@ResponseBody
	public int updateExamById(User user, Exam exam) {
		int state = userService.updateExamById(exam.getId(), exam);
		return state;
	}

	@RequestMapping("/del_user")
	@ResponseBody
	public int deleteUserById(User user) {
		return userService.deleteUserById(user.getId());
	}

	@RequestMapping("/index")
	public String index() {
		System.out.println("hello");
		return "index";
	}

	@RequestMapping("/success")
	public String success() {
		return "success";
	}

	@RequestMapping("/noaccess")
	public String noAccess() {
		return "noaccess";
	}

	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	@ResponseBody
	public String toAdd() {

		return "add user";
	}

	@RequestMapping("/add_users")
	@ResponseBody
	public int addUsers() {
		List<User> users = new ArrayList<>();
		String password = "123";
		String username = "Vamy";
		int permission = 0;
		for (int i = 0; i < 10; i++) {
			User u = new User();
			u.setUsername(username + i);
			u.setPassword(password + i);
			u.setPermission(permission);
			users.add(u);
			u = null;
		}

		// 插入数据
		int count = userService.addUsers(users);
		return count;
	}

	// 表单提交的内容自动注入到user
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public String addUser(User user, User user2, HttpServletRequest request, Model model) {
		System.out.println(user);
		System.out.println(user2);
		System.out.println(user == user2);// 结果为true，表明是同一个对象实例
		return "redirect:/base/success";
	}

	// 数据绑定 编辑Date类型数据
	@InitBinder
	public void bindDate(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
