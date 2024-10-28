<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Product Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table-hover tbody tr:hover {
            background-color: #f5f5f5;
        }
        .table th, .table td {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="row mb-4">
        <div class="col-md-12 text-center">
            <h3 class="display-4">Product List</h3>
            <p class="lead text-muted">Overview of categories, their total price, and quantity.</p>
        </div>
    </div>

    <!-- Table for Displaying Products -->
    <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered">
            <thead class="thead-dark">
            <tr>
                <th><a href="/productSort/Name" class="text-white">Category Name</a></th>
                <th>Total Price ($)</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.group.name}</td>
                    <td>${category.sum} $</td>
                    <td><fmt:formatNumber value="${category.count}" type="number" maxFractionDigits="0" minFractionDigits="0" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
         <a href="/Lab6" class="btn btn-primary login-button">Quay Về Trang Chủ</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
