<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Details</title>
</head>
<body>
    <h2>Student Information</h2>

    <p><strong>Student ID:</strong> ${student.id}</p>
    <p><strong>Last Name:</strong> ${student.lastName}</p>
    <p><strong>First Name:</strong> ${student.firstName}</p>
    <p><strong>Gender:</strong> ${student.gender}</p>
    <p><strong>Country:</strong> ${student.country}</p>

    <!-- Thêm nút để quay lại form nhập -->
    <form action="saveStudent" method="get">
        <input type="submit" value="Back to Form">
    </form>
</body>
</html>
