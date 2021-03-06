<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Section Creation</title>
</head>
<body>
<jsp:include page="/currentLoginUserInfo.jsp" />
<h1>Section Creation</h1>
<h2>Please select teacher, subject, and schedule</h2>
<form action="${pageContext.request.contextPath}/SectionCreation" method="post">
Section Number:<input type="text" size="20" name="sectionNumber"><br>
<p>Teacher:
<select name="facultyNumber">
<c:forEach var="faculty" items="${facultyList}">
 <option value="${ faculty.facultyNumber }"> ${ faculty.facultyNumber }</option>
</c:forEach>
</select>
</p>
<p>Subject:
<select name="subjectId">
<c:forEach var="subject" items="${subjectList}">
 <option value="${ subject.subjectId }"> ${ subject.subjectId }</option>
</c:forEach>
</select>
</p>
<p>Schedule: Day
<select name="day">
<c:forEach var="day" items="${daysList}">
 <option value="${ day }"> ${ day }</option>
</c:forEach>
</select>
Period
<select name="period">
<c:forEach var="period" items="${periodList}">
 <option value="${ period }"> ${ period }</option>
</c:forEach>
</select>
</p>
<input type="submit" value="Submit">
</form>
</body>
</html>