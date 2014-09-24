<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Section Creation Result</title>
</head>
<body>
<c:choose>
	<c:when test = "${empty sectionCreationError }">
		<h1>Section is created as below:</h1>
		<p>Section Number: ${ createdSection.sectionNumber}</p>
		<p>Teacher : ${createdSection.instructor }</p>
		<p>Subject : ${createdSection.subject }</p>
		<p>Schedule: ${createdSection.schedule }</p>
	</c:when>
	<c:otherwise>
		<h1>${ sectionCreationError}</h1>
	</c:otherwise>
</c:choose>
<a href="sectionCreation.jsp">Back to Section Creation</a>
</body>
</html>