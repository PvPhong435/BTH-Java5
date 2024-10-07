<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Add Product</h2>
    <form:form action="/productSaveAdd" method="post" modelAttribute="product" enctype="multipart/form-data">
        <div class="form-group">
            <label>ID</label>
            <form:input path="id" class="form-control"  />
            <form:errors path="id" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Name</label>
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Price</label>
            <form:input path="price" class="form-control" />
            <form:errors path="price" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Create Date</label>
            <form:input path="createDate" class="form-control" type="date"/>
            <form:errors path="createDate" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Available</label>
            <form:checkbox path="available" /> Available
        </div>
        <div class="form-group">
            <label>Category</label>
            <form:select path="category.id" class="form-control">
                <form:option value="" label="Select Category" />
                <c:forEach items="${categories}" var="category">
                    <form:option value="${category.id}" label="${category.name}" />
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <label>Product Images</label>
            <input type="file" name="productImages" class="form-control" multiple />
            
        </div>
        <button type="submit" class="btn btn-primary">Add Product</button>
    </form:form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
