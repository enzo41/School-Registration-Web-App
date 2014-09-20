<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
You are logged in as ${ user.name }, with User ID ${ user.userId } and type ${ user.type }.
</div>

<div>
<h2>What would you like to do?</h2>
<ul>
	<li><a href="/enlist">Enlist</a>
</ul>
</div>

</body>
</html>