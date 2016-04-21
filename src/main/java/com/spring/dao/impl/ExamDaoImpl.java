package com.spring.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import com.spring.dao.ExamDao;
import com.spring.model.Exam;
import com.spring.model.ExamInfo;
import com.spring.model.User;

@Component
public class ExamDaoImpl implements ExamDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Exam exam) {
		Session session = sessionFactory.getCurrentSession();
		session.save(exam);

	}

	@Override
	public Exam getExamById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Exam exam = (Exam) session.get(Exam.class, id);
		return exam;
	}

	@Override
	public Set<Exam> getExamsByUserId(int id) {
		Session session = sessionFactory.getCurrentSession();
		User u = (User) session.get(User.class, id);// 只查询user表
		Set<Exam> exams = u.getExams();// 此时不去数据库查询数据
		System.out.println(exams.iterator().next().getScore());// 去数据库查数据（exam表）
		return exams;
	}

	@Override
	public int addExam(int id, Exam exam) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(id);
		exam.setUser(user);
		session.save(exam);
		return 1;
	}

	@Override
	public int updateExamById(int id, Exam exam) {
		Session session = sessionFactory.getCurrentSession();
		Exam exam1 = (Exam) session.get(Exam.class, id);
		if (exam1 == null) {
			return -1;
		}
		exam1.setScore(exam.getScore());// 自动调用更新语句（此时exam1为持久化对象，在session关闭之前改变该对象属性值，则会更新数据库的记录）
		// session.update(exam1);
		return 1;
	}

	/**
	 * 多条件查询（条件为空则忽略）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> queryExams(ExamInfo examInfo) {
		Session session = sessionFactory.getCurrentSession();

		StringBuffer hql = new StringBuffer();
		hql.append("from Exam e where e.user.username like :name");
		if (examInfo.getName() == null) {
			examInfo.setName("");
		}
		Query query = dynamicQuery(session, hql, examInfo);
		// 执行查询
		List<Exam> exams = (List<Exam>) query.list();
		return exams;
	}

	public Query dynamicQuery(Session session, StringBuffer hql, ExamInfo examInfo) {
		// 未输入任何搜索条件
		Query query = null;
		if (examInfo.getExamType() == 0 & examInfo.getWorkType() == 0 & examInfo.getScore() == 0
				& examInfo.getScore2() == 0 & examInfo.getTestTime() == null & examInfo.getTestTime2() == null
				& examInfo.getUser() == null) {
			query = session.createQuery(hql.toString());
			query.setString("name", "%" + examInfo.getName() + "%");
			return query;
		}
		// 存储参数，动态组合hql
		Map<String, Object> map = setQueryParams(hql, examInfo);

		System.out.println(hql.toString());
		query = session.createQuery(hql.toString());
		// 设置query参数
		query.setString("name", "%" + examInfo.getName() + "%");
		query.setInteger("score", (int) map.get("score"));
		if (map.containsKey("workType")) {
			query.setInteger("workType", (int) map.get("workType"));
		}
		if (map.containsKey("examType")) {
			query.setInteger("examType", (int) map.get("examType"));
		}
		if (map.containsKey("score2")) {
			query.setInteger("score2", (int) map.get("score2"));
		}
		if (map.containsKey("testTime")) {
			query.setDate("testTime", (Date) map.get("testTime"));
		}
		if (map.containsKey("testTime2")) {
			query.setDate("testTime2", (Date) map.get("testTime2"));
		}
		return query;
	}

	public Map<String, Object> setQueryParams(StringBuffer hql, ExamInfo examInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("score", examInfo.getScore());
		hql.append(" and e.score>=:score\n");
		if (examInfo.getWorkType() > 0) {
			map.put("workType", examInfo.getWorkType());
			hql.append(" and e.workType=:workType\n");
		}
		if (examInfo.getExamType() > 0) {
			map.put("examType", examInfo.getExamType());
			hql.append(" and e.examType=:examType\n");
		}
		if (examInfo.getScore2() > examInfo.getScore()) {
			map.put("score2", examInfo.getScore2());
			hql.append(" and e.score<=:score2\n");
		} else {
			map.put("score2", 100);
			hql.append(" and e.score<=:score2\n");
		}
		if (examInfo.getTestTime() != null) {
			map.put("testTime", examInfo.getTestTime());
			hql.append(" and e.testTime>=:testTime\n");
		}

		if (examInfo.getTestTime2() != null) {
			map.put("testTime2", examInfo.getTestTime2());
			hql.append(" and e.testTime<=:testTime2\n");
		}
		return map;
	}

}
