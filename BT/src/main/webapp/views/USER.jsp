<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            max-width: 400px;
            margin: auto;
        }
        .search-container {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-bottom: 15px;
        }
        .search-container input,
        .search-container select,
        .search-container button {
            height: 38px;
        }
        .table-container {
            margin-top: 10px;
        }
        .clickable-row {
            cursor: pointer;
        }
        .link-row {
            text-decoration: none;
            color: inherit; /* Thừa hưởng màu chữ từ hàng */
        }
        .table-striped tbody tr:hover {
            background-color: #f5f5f5; /* Đổi màu khi hover */
        }
    </style>
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">User Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
		<div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav ml-auto">
		        <c:choose>
		            <c:when test="${empty account}">
		                <li class="nav-item">
		                    <a class="nav-link btn btn-info" href="/Login">Đăng nhập</a>
		                </li>
		            </c:when>
		            <c:otherwise>
		                <li class="nav-item d-flex align-items-center">
		                    <span class="mr-2">Tên: ${account.fullname} | </span>
		                    <span class="mr-2">Vai Trò: 
		                        <c:choose>
		                            <c:when test="${account.admin}">Admin</c:when>
		                            <c:otherwise>User</c:otherwise>
		                        </c:choose>
		                    </span>
		                    <a class="btn btn-danger btn-sm" href="/LogOut">Đăng xuất</a>
		                </li>
		            </c:otherwise>
		        </c:choose>
		    </ul>
		</div>

</nav>

<div class="container mt-5">
    <!-- Form Nhập Liệu Người Dùng -->
    <div class="form-container bg-light p-4 rounded shadow-sm">
        <h2 class="text-center">User Form</h2>
        <form:form method="POST" modelAttribute="user" action="/AddOrUpdate">
            <div class="form-group">
                <label for="id">User ID</label>
                <form:input path="id" class="form-control" readonly="${checkRead}" />
                <form:errors path="id" cssClass="text-danger" />
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <form:input path="password" class="form-control" />
                <form:errors path="password" cssClass="text-danger" />
            </div>

            <div class="form-group">
                <label for="fullname">Full Name</label>
                <form:input path="fullname" class="form-control" />
                <form:errors path="fullname" cssClass="text-danger" />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" class="form-control"  />
                <form:errors path="email" cssClass="text-danger" />
            </div>

            <div class="form-group">
                <label>Role</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" name="admin" value="true" id="admin" 
                           class="form-check-input" <c:if test="${user.admin}">checked</c:if> />
                    <label class="form-check-label" for="admin">Admin</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" name="admin" value="false" id="user" 
                           class="form-check-input" <c:if test="${!user.admin}">checked</c:if> />
                    <label class="form-check-label" for="user">User</label>
                </div>
            </div>

            <div class="d-flex justify-content-between">
                <!-- Nút Thêm: Kiểu submit -->
                <button type="submit" class="btn btn-success" 
                        <c:if test="${checkSelected}">style="pointer-events: none; opacity: 0.5;"</c:if>>
                    Thêm
                </button>
                
                <!-- Nút Sửa: Kiểu submit -->
                <button type="submit" class="btn btn-warning" 
                        <c:if test="${!checkSelected}">style="pointer-events: none; opacity: 0.5;"</c:if>>
                    Sửa
                </button>
                
                <!-- Nút Xóa: Chỉ có thể sử dụng khi checkSelected là true -->
                <a href="/Remove/${user.id}" class="btn btn-danger" 
                   <c:if test="${!checkSelected}">style="pointer-events: none; opacity: 0.5;"</c:if>>
                   Xóa
                </a>
                
                <!-- Nút Làm Mới: Luôn có thể sử dụng -->
                <a href="/Refesh" class="btn btn-info">Làm Mới</a>
            </div>

        </form:form>
        <!-- Thông báo lỗi -->
        <c:if test="${not empty errMess}">
            <div class="alert alert-danger mt-3" role="alert">
                ${errMess}
            </div>
        </c:if>
        
        <!-- Thông báo thành công -->
        <c:if test="${not empty Mess}">
            <div class="alert alert-success mt-3" role="alert">
                ${Mess}
            </div>
        </c:if>
    </div>

    <h3 class="mt-4 mb-0 text-center">User List</h3>
	<form id="searchForm" action="/Search" method="GET" class="row mb-4">
	    <div class="col-md-4">
	        <label for="fullname" class="form-label">Tìm theo tên</label>
	        <input type="text" name="fullname" id="fullname" class="form-control" placeholder="Nhập tên" value="${fullname}">
	    </div>
	    <div class="col-md-3">
	        <label for="priceMin" class="form-label">Giá từ</label>
	        <input type="number" name="priceMin" id="priceMin" class="form-control" placeholder="Giá từ" value="${priceMin}">
	    </div>
	    <div class="col-md-3">
	        <label for="priceMax" class="form-label">Giá đến</label>
	        <input type="number" name="priceMax" id="priceMax" class="form-control" placeholder="Giá đến" value="${priceMax}">
	    </div>
	    <div class="col-md-2">
	        <label for="sort" class="form-label">Sắp xếp theo giá</label>
	        <select name="sortOrder" id="sort" class="form-select">
	            <option value="non" ${sortOrder == 'non' ? 'selected' : ''}>Không Sắp Xếp</option>
	            <option value="asc" ${sortOrder == 'asc' ? 'selected' : ''}>Tăng dần</option>
	            <option value="desc" ${sortOrder == 'desc' ? 'selected' : ''}>Giảm dần</option>
	        </select>
	    </div>
	    <div class="col-md-12 mt-3">
	        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
	        <a href="/Thi" class="btn btn-danger ms-3">Tải Lại Trang</a>
	    </div>
	</form>
    <!-- Bảng Hiển Thị Danh Sách Người Dùng -->
    <div class="table-container">
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
                    <tr class="clickable-row" onclick="window.location.href='/Detail/${user.id}'">
                        <td>${user.id}</td>
                        <td>${user.fullname}</td>
                        <td>${user.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.admin}">Admin</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose>
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
