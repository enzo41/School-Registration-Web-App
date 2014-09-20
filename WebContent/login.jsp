<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post">

		<h2>Who are you?</h2>

		<c:forEach var="user" items="${users}">
			<p><input type="radio" name="userId" value="${user.userId }">${user.name } (${user.type })
		</c:forEach>

		<div>
			<input type="submit" value="Submit">
		</div>


	</form>
</body>
</html>