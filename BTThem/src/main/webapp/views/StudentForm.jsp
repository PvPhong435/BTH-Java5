<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/standard-1.2" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Form</title>
</head>
<body>
    <h2>Student Information Form</h2>

    <!-- Spring Form -->
    <form:form action="/saveStudent" modelAttribute="student" method="post">
        
        <!-- ID -->
        <label for="id">Student ID:</label><br>
        <form:input path="id" id="id" required="true" /><br><br>

        <!-- Last Name -->
        <label for="lastName">Last Name:</label><br>
        <form:input path="lastName" id="lastName" required="true" /><br><br>

        <!-- First Name -->
        <label for="firstName">First Name:</label><br>
        <form:input path="firstName" id="firstName" required="true" /><br><br>

        <!-- Gender -->
        <label for="gender">Gender:</label><br>
        <form:radiobutton path="gender" items="${gender}" delimiter=" " />
        

        <!-- Country (Combobox) -->
        <label for="country">Country:</label><br>
        <form:select path="country.id" required="true">
            <form:option items="${country}" itemValue="id" itemLabel="name"/>
        </form:select><br><br>

        <!-- Save Button -->
        <input type="submit" value="Save">
    </form:form>
</body>
</html>
