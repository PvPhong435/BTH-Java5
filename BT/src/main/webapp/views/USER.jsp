<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            max-width: 400px; /* Giới hạn chiều rộng */
            margin: auto; /* Căn giữa */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="form-container bg-light p-4 rounded shadow-sm">
        <h2 class="text-center">User Form</h2>
        <form:form method="POST" modelAttribute="user">
            <div class="form-group">
                <label for="id">User ID</label>
                <form:input path="id" class="form-control" required="true" />
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <form:password path="password" class="form-control" required="true" />
            </div>

            <div class="form-group">
                <label for="fullname">Full Name</label>
                <form:input path="fullname" class="form-control" required="true" />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" class="form-control" required="true" />
            </div>

            <div class="form-group">
                <label>Role</label><br>
                <div class="form-check form-check-inline">
                    <form:checkbox path="admin" class="form-check-input" />
                    <label class="form-check-label" for="admin">Admin</label>
                </div>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">Thêm</button>
                <button type="submit" class="btn btn-warning">Sửa</button>
                <button type="submit" class="btn btn-danger">Xóa</button>
                <button type="reset" class="btn btn-info">Làm mới</button>
            </div>
        </form:form>
    </div>

    <div class="table-responsive mt-5">
        <h3 class="text-center">User List</h3>
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.fullname}</td>
                        <td>${user.email}</td>
                        <td>
                            <label>
                                <input type="radio" name="role" value="true" 
                                    <c:if test="${user.admin}">checked</c:if>> Admin
                            </label>
                            <label>
                                <input type="radio" name="role" value="false" 
                                    <c:if test="${!user.admin}">checked</c:if>> User
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
