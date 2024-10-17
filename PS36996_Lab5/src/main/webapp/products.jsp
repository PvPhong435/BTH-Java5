<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-5">
		<!-- Sorting Dropdown -->
		<form method="get" action="/products">
			<div class="form-group">
				<label for="sort">Sort By:</label> <select class="form-control"
					id="sort" name="sort">
					<option value="name"
						<c:if test="${param.sort == 'name'}">selected</c:if>>Name</option>
					<option value="price"
						<c:if test="${param.sort == 'price'}">selected</c:if>>Price</option>
					<option value="category"
						<c:if test="${param.sort == 'category'}">selected</c:if>>Category</option>
				</select>
			</div>
			<div class="form-group">
				<label for="sort">Search:</label>
				 <input type="text" class="form-control" name="key">
			</div>
			<button type="submit" class="btn btn-primary">Sort</button>
		</form>
	</div>
	<div class="container mt-5"></div>
	<div class="container mt-5">
		<div class="row">
			<c:forEach items="${products}" var="product" varStatus="status">
				<div class="col-md-4 mb-4">
					<div class="card" style="width: 18rem;">
						<img src="${product.images[0].id}" class="card-img-top img-fluid"
							alt="Product Image">
						<div class="card-body">
							<h5 class="card-title">${product.name}</h5>
							<h5 class="card-text">${product.category.name}</h5>
							<p class="card-text">${product.description}</p>
							<h6>${product.price}vnd</h6>
							<div class="row">
								<div class="col-6">
									<a href="/addcart/${product.id}" class="btn btn-primary w-100">Add To Cart</a>
								</div>
								<div class="col-6">
									<a href="/product/${product.id}" class="btn btn-danger w-100">Detail</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${status.index % 3 == 2}">
					<div class="row"></div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>