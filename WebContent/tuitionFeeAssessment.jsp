<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tuition Fee Assessment</title>
</head>
<body>
<jsp:include page="/currentLoginUserInfo.jsp" />
<c:choose>
	<c:when test = "${empty tuitionFeeAssessmentError }">
		<h1>${tuitionFeeAssessment.term } Tuition Fee Assessment</h1>
		<p>Enlisted Undergraduate Subject :  Quantity ${ tuitionFeeAssessment.numberOfEnlistedUndergraduateSubject}, Fee per subject P2,000, Subtotal P${ tuitionFeeAssessment.totalUndergraduateSubjctFee}   </p>
		<p>Enlisted Graduate Subject	  :	 Quantity ${ tuitionFeeAssessment.numberOfEnlistedGraduateSubject }, Fee per subject P4000, Subtotal P${ tuitionFeeAssessment.totalGraduateSubjectFee}</p>
		<p>Scholarship covers ${ tuitionFeeAssessment.student.shorlarshipStatus } of subject tuition fee</p>
		<p>Miscellaneous Fee : P${ tuitionFeeAssessment.miscellaneousFee }</p>
		<p>Total Tuition fee : P${ tuitionFeeAssessment.totalTuitionFee }</p>
	</c:when>
	<c:otherwise>
		<h1>${tuitionFeeAssessment.term } Tuition Fee Assessment</h1>
		<p> ${ tuitionFeeAssessmentError }</p>
	</c:otherwise>
</c:choose>
<a href="index.jsp">Back to Menu Page</a>
</body>
</html>