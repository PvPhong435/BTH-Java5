<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Product Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <!-- Carousel for Product Images -->
<div class="col-md-6">
    <div id="productCarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
		<c:choose>
		    <c:when test="${empty product.productImages}">
		        <div class="carousel-item active">
		            <img src="/Image/NullPic.jpg" class="d-block" alt="Default Image" style="max-width: 100%; height: auto;">
		        </div>
		    </c:when>
		    <c:otherwise>
		        <c:forEach var="image" items="${product.productImages}" varStatus="status">
		            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
		                <img src="/Image/${image.imageLink}" class="d-block w-100" alt="${product.name}" style="max-width: 100%; height: auto;">
		            </div>
		        </c:forEach>
		    </c:otherwise>
		</c:choose>

        
           

        </div>
        <a class="carousel-control-prev" href="#productCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#productCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>


        <!-- Product Information -->
        <div class="col-md-6">
            <form:form action="/productSave" method="post" modelAttribute="product">
		        <div class="form-group">
		            <label>ID</label>
		            <form:input path="id" class="form-control" readonly="true" />
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
		            <form:input path="createDate" class="form-control" type="date" />
		            <form:errors path="createDate" cssClass="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Available</label>
		            <form:checkbox path="available" />
		        </div>
		       <div class="form-group">
				    <label>Category</label>
				    <form:select path="category.id" class="form-control">
				        <form:option value="" label="Select Category" /> <!-- Tùy chọn mặc định -->
				        <c:forEach items="${categories}" var="category">
				            <form:option value="${category.id}" label="${category.name}" />
				        </c:forEach>
				    </form:select>
				</div>
		        <button type="button" class="btn btn-primary" onclick="window.location.href='/productAddNew';">Add</button>
		        <button type="submit" class="btn btn-secondary">Update</button>
		        <button type="button" class="btn btn-danger" onclick="window.location.href='/productRemove/${product.id}';">Remove</button>
				<button type="button" class="btn btn-danger" onclick="window.location.href='/productList';">Back To Product List</button>
		    </form:form>
        </div>
    </div>

    <!-- Table for Displaying Products -->
    <h3>Product List</h3>
    <table class="table">
        <thead>
        <tr>
            <th><a href="/productSort/id">ID</a></th>
            <th><a href="/productSort/name">Name</a></th>
            <th><a href="/productSort/price">Price</a></th>
            <th><a href="/productSort/CreateDate">Create Date</a></th>
            <th><a href="/productSort/Available">Available</a></th>
            <th><a href="/productSort/">Category ID</a></th>
            <th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="prod">
        
            <tr onclick="window.location='/productDetail/${prod.id}'" style="cursor:pointer;">
                <td>${prod.id}</td>
                <td>${prod.name}</td>
                <td>${prod.price}</td>
                <td>${prod.createDate}</td>
                <td>${prod.available}</td>
                <td>${prod.category.name}</td>
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
