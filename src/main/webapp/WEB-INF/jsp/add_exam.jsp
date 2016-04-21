<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加考试记录</title>
</head>
<body>
	<form action="" method="post">
		用户ID：<input type="text" name="id"><br> 
		试题类型： <select
			name="examType">
			<option value="0">模拟考试</option>
			<option value="1">真实考试</option>
		</select><br> 
		工种：<select name="workType">
			<option value="0">1组</option>
			<option value="1">2组</option>
		</select><br> 
		分数：<input type="text" name="score"><br>
        <input type="submit" value="提交">
	</form>
</body>
</html>