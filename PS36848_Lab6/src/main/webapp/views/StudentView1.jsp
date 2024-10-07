<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Student</title>
</head>
<body>
    <h2>Danh sách Student</h2>
    <table border="1">
        <thead>
            <tr>
                <th><a href="/sort/id">ID</a></th>
                <th><a href="/sort/Name">Name</a></th>
                <th><a href="/sort/Phone">Phone</a></th>
                <th><a href="/sort/Address">Address</a></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students.content}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.phone}</td>
                    <td>${student.address}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div>
        <a class="page-link" href="/page?page=0">First</a>
        <a class="page-link" href="/page?page=${students.number - 1}">Previous</a>
        <span>Trang ${currentPage + 1} / ${students.totalPages }</span>
        <a class="page-link" href="/page?page=${students.number + 1}">Next</a>
        <a class="page-link" href="/page?page=${students.totalPages - 1 }">Last</a>
    </div>
</body>
</html>
