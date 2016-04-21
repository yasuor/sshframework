<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试记录查询</title>
</head>
<body>
	<form action="" method="post">
		姓名：<input type="text" name="name"><br> 
		试题类型： <select
			name="examType">
			<option value="0">全部</option>
			<option value="1">模拟考试</option>
			<option value="2">真实考试</option>
		</select><br> 
		工种：<select name="workType">
			<option value="0">全部</option>
			<option value="1">1组</option>
			<option value="2">2组</option>
		</select><br> 
		分数：<select name="score">
				<option value="0" selected="selected">0</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
				<option value="60">60</option>
				<option value="70">70</option>
				<option value="80">80</option>
				<option value="90">90</option>
				<option value="100">100</option>
			</select>&nbsp;~&nbsp;
			<select name="score2">
				<option value="0">0</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
				<option value="60">60</option>
				<option value="70">70</option>
				<option value="80">80</option>
				<option value="90">90</option>
				<option value="100" selected="selected">100</option>
			</select><br>
		考试时间：<input type="text" name="testTime">&nbsp;~&nbsp;<input type="text" name="testTime2"><br>
		
        <input type="submit" value="提交">
	</form>
</body>
</html>