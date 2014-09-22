<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>

	<h1>Enlistment Result</h1>

	<h2>Successful Enlistments</h2>
	<table border="1">
	<tr><td>Section Number</td><td>Subject</td><td>Schedule</td></tr>
		<c:forEach var="section" items="${result.successfullyEnlisted}">
			<tr><td>${section.sectionNumber }</td><td>${section.subject.subjectId }</td><td>${section.schedule.days } ${section.schedule.period }</td></tr>
		</c:forEach>
	</table>

	<h2>Unsuccessful Enlistments</h2>
	<table border="1">
	<tr><td>Section Number</td><td>Subject</td><td>Schedule</td><td>Reason</td></tr>
		<c:forEach var="entry" items="${result.failedToEnlist}">
			<tr><td>${entry.key.sectionNumber  }</td> <td>${entry.key.subject.subjectId  }</td><td>${entry.key.schedule.days } ${entry.key.schedule.period }</td><td> ${entry.value }</td></tr>
		</c:forEach>
	</table>

</body>
</html>