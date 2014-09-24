<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<jsp:include page="/currentLoginUserInfo.jsp" />

<div>
<h2>What would you like to do?</h2>
<ul>
	<c:if test="${ user.type == 'STUDENT' }">
	<li><strong><a href="${pageContext.request.contextPath}/showSections">Enlist Sections</a></strong></li><br>
	<li><strong><a href="${pageContext.request.contextPath}/TuitionFeeAssessment">Tuition Fee Assessment</a></strong></li>
	</c:if>
	<c:if test="${ user.type == 'ADMIN' }">
	<li><strong><a href="${pageContext.request.contextPath}/SectionCreation">Create New Sections</a></strong></li>
	</c:if>
</ul>
</div>

</body>
</html>