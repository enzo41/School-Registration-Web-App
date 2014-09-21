<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>O &amp; B University</title>
</head>
<body>
	<div>You are logged in as ${ user.name }, with User ID ${ user.userId }
		and type ${ user.type }.</div>
	<h1>Enlist in Sections</h1>
	<form action="enlist" method="post">

		<h2>Choose Your Sections</h2>
		<table border="1">
		<tr><td></td><td>Section Number</td><td>Subject</td><td>Schedule</td></tr>
			<c:forEach var="section" items="${sections}">
			<tr><td><input type="checkbox" name="sectionNumber"
					value="${section.sectionNumber }"></td><td>${section.sectionNumber }</td><td>${section.subject.subjectId }</td><td>${section.schedule.days } ${section.schedule.period }</td></tr>
				
		</c:forEach>
		</table>
		<div>
			<input type="submit" value="Submit">
		</div>
	</form>
</body>
</html>