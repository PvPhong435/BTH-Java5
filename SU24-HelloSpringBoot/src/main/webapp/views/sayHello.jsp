<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Say hello Page</title>
</head>
<body>
<!--  <h1> Cookie information: ${java5Key}</h1> -->
<h1>${courseInfo}</h1>
 <h2>${message}</h2> 
 <h2>Age: ${age }</h2>
 <h2>Cookie value: ${java5cookie}</h2>
 <br/>
 <h2>Student Information</h2>
 <h2>Student ID: ${st.id}</h2>
 <h2>Last Name: ${st.lastName}</h2>
 <h2>First Name: ${st.firstName}</h2>
 <h2>Phone Number: ${st.phoneNumber}</h2>
 <h2>Email: ${st.email}</h2>
</body>
</html>