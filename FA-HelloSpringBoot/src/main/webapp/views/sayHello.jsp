<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page</title>
</head>
<body>
<h2>${welcomeMessage }</h2>
<h3>Username: ${username }</h3>
<h3>Shop name: ${shopName }</h3>
</body>
</html>