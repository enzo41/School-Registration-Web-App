<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>O &amp; B University</title>
</head>
<body>
<div>
You are logged in as ${ user.name }, with User ID ${ user.userId } and type ${ user.type }.
</div>
<h1>Enlist in Sections</h1>
<form action="enlist" method="post">

<h2>Choose Your Sections</h2>
<input type="checkbox" name="sectionNumber" value="AAA111">Math 11 - AAA111
<input type="checkbox" name="sectionNumber" value="BBB222">Math 14 - BBB222
<input type="checkbox" name="sectionNumber" value="CCC333">Math 53 - CCC333

<div>
<input type="submit" value="Submit">
</div>
</form>
</body>
</html>