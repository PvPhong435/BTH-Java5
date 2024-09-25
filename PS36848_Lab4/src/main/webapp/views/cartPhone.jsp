<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shopping Cart</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0">Shopping Cart</h4>
						<span class="badge badge-light float-right">${Count} items</span>
					</div>
					<div class="card-body">
						<c:forEach var="items" items="${cart.items}">
							<div class="row align-items-center border-bottom pb-3 mb-3">
								<div class="col-2">
									<img src="/image/${items.image}" class="img-fluid" style="max-width: 100%; height: auto;">
								</div>
								<div class="col-4">
									<p class="text-muted mb-1">ID: ${items.id}</p>
									<h5 class="mb-0">${items.name}</h5>
								</div>
								<div class="col-2">
									<a href="/update/${items.id}/minus" class="btn btn-outline-secondary btn-sm">-</a>
									<span class="mx-2">${items.qty}</span>
									<a href="/update/${items.id}/plus" class="btn btn-outline-secondary btn-sm">+</a>
								</div>
								<div class="col-3 text-right">
									<h6 class="mb-0">&euro; ${items.price * items.qty}</h6>
								</div>
								<div class="col-1 text-right">
									<a href="/remove/${items.id}" class="text-danger"><i class="fa fa-times"></i></a>
								</div>
							</div>
						</c:forEach>
						<div class="mt-3">
							<a href="/" class="btn btn-outline-primary">Back to shop</a>
							<a href="/clear" class="btn btn-danger float-right">Clear Cart</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card">
					<div class="card-header bg-success text-white">
						<h5 class="mb-0">Summary</h5>
					</div>
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<p class="mb-0">Items</p>
							<p class="mb-0">${count}</p>
						</div>
						<div class="d-flex justify-content-between">
							<p class="mb-0">Subtotal</p>
							<p class="mb-0">&euro; ${Amount}</p>
						</div>
						<hr>
						<form>
							<div class="form-group">
								<label for="shipping">Shipping</label>
								<select id="shipping" class="form-control">
									<option>Standard Delivery - &euro;5.00</option>
								</select>
							</div>
							<div class="form-group">
								<label for="code">Promo Code</label>
								<input type="text" id="code" class="form-control" placeholder="Enter your code">
							</div>
						</form>
						<hr>
						<div class="d-flex justify-content-between">
							<h5>Total</h5>
							<h5>&euro; ${Amount + 5}</h5>
						</div>
						<a href="/gotoPay" class="btn btn-outline-primary">Back to shop</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
