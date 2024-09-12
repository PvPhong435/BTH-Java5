<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to My First Spring Boot App</title>
</head>
<body>
	<h1>Home Page</h1>
	<h3>${message}</h3>
	<h1>${courseInfo}</h1>
 
 <h2>Age: ${age }</h2>
 
 <br/>
 <h2>Student Information</h2>
 <h2>Student ID: ${st.id}</h2>
 <h2>Last Name: ${st.lastName}</h2>
 <h2>First Name: ${st.firstName}</h2>
 <h2>Phone Number: ${st.phoneNumber}</h2>
 <h2>Email: ${st.email}</h2>
</body>
</html>