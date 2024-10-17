<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center mt-5">Login</h2>

                <!-- Spring Form with JSP -->
                <form:form action="/Login" modelAttribute="account" method="post" class="mt-4">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input path="username" cssClass="form-control" id="username" placeholder="Enter username"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:password path="password" cssClass="form-control" id="password" placeholder="Enter password"/>
                    </div>

					<!-- Error message if login fails -->
					<c:if test="${not empty error}">
					    <div class="alert alert-danger" role="alert">
					        <c:choose>
					            <c:when test="${error == 'Optional'}">
					                Please login!
					            </c:when>
					            <c:otherwise>
					                ${error}
					            </c:otherwise>
					        </c:choose>
					    </div>
					</c:if>


                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                </form:form>
                <a href="/Thi" class="btn btn-primary login-button mt-4">Quay Về Trang Chủ</a>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
