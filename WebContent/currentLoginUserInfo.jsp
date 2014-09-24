<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<div>
You are logged-in as <strong>${ user.name }</strong>, with User ID <strong>${ user.userId }</strong> and type <strong>${ user.type }</strong>.
</div>
</html>