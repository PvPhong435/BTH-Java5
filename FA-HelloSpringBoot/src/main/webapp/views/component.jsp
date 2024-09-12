<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample using Spring MVC Components</title>
</head>
<body>
<h2>${age }</h2>
<h2>${username }</h2>
<h2>${shopName }</h2>
<h1>Student information: </h1>
<h2>ID: ${student.id }</h2>
<h2>Full Name: ${student.lastName } ${student.firstName }</h2>
${student }
</body>
</html>