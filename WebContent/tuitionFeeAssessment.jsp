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
		<h1><u>Tuition Fee Assessment of ${tuitionFeeAssessment.term }</u></h1>
		<h4>Enlisted Undergraduate Subject: &nbsp; <u>P${ tuitionFeeAssessment.totalUndergraduateSubjctFee}</u></h4>
		<p>Quantity ${ tuitionFeeAssessment.numberOfEnlistedUndergraduateSubject}, &nbsp; Fee per subject P${ tuitionFeeAssessment.feePerUndergraduateSubject}</p>
		<h4>Enlisted Graduate Subject:  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <u>P${ tuitionFeeAssessment.totalGraduateSubjectFee}</u></h4>
		<p>Quantity ${ tuitionFeeAssessment.numberOfEnlistedGraduateSubject }, &nbsp; Fee per subject P${ tuitionFeeAssessment.feePerGraduateSubject}</p>
		<c:if test="${ tuitionFeeAssessment.student.shorlarshipStatus != 'NONE' }">
		<h4>Scholarship covers <u>${ tuitionFeeAssessment.student.shorlarshipStatus }</u> subject fee</h4>
		</c:if>
		<h4>Miscellaneous Fee : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>P${ tuitionFeeAssessment.miscellaneousFee }</u></h4>
		<h3>Total Tuition Fee : <u>P${ tuitionFeeAssessment.totalTuitionFee }</u></h3>
	</c:when>
	<c:otherwise>
		<h1>${tuitionFeeAssessment.term } Tuition Fee Assessment</h1>
		<p> ${ tuitionFeeAssessmentError }</p>
	</c:otherwise>
</c:choose>
<a href="index.jsp">Back to Menu Page</a>
</body>
</html>