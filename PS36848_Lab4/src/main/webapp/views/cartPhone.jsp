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
<style>
    .custom-header {
        background-color: #dc3545; /* Màu nền (bg-danger) */
        color: white; /* Đổi màu font thành đỏ */
        border-left: 2px solid #dc3545; /* Viền bên trái */
        border-right: 2px solid #dc3545; /* Viền bên phải */
        border-top-left-radius: 10px; /* Bo tròn góc trên bên trái */
        border-top-right-radius: 10px; /* Bo tròn góc trên bên phải */
    }
</style>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-8">
				<div class="card">
					<div class="card-header custom-header">
					    <h4 class="mb-0">Shopping Cart</h4>
					    <span class="badge badge-light float-right">${Count} items in cart</span>
					</div>
					<div class="card-body">
						<c:forEach var="items" items="${cart.phones}">
							<div class="row align-items-center border-bottom pb-3 mb-3">
								<div class="col-2">
									<img src="/image/${items.image[0]}" class="img-fluid" style="max-width: 100%; height: auto;">
								</div>
								<div class="col-4">
									<p class="text-muted mb-1">ID: ${items.id}</p>
									<h5 class="mb-0">${items.name}</h5>
								</div>
								<div class="col-2">
									<a href="/phone/update/${items.id}/minus" class="btn btn-outline-secondary btn-sm">-</a>
									<span class="mx-2">${items.qty}</span>
									<a href="/phone/update/${items.id}/plus" class="btn btn-outline-secondary btn-sm">+</a>
								</div>
								<div class="col-3 text-right">
									<h6 class="mb-0">$ ${items.price * items.qty}</h6>
								</div>
								<div class="col-1 text-right">
									<a href="/phone/remove/${items.id}" class="text-danger"><i class="fa fa-times"></i></a>
								</div>
							</div>
						</c:forEach>
						<div class="mt-3">
							<a href="/Phone" class="btn btn-outline-primary">Back to shop</a>
							<a href="/phone/clear" class="btn btn-danger float-right">Clear Cart</a>
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
							<p class="mb-0">$ ${Amount}</p>
						</div>
						<hr>
						<form id="shipping-form">
						    <div class="form-group">
						        <label for="from_district">From District</label>
						        <select id="from_district" class="form-control">
						            <option value="">Select From District</option>
						            <!-- Các quận/huyện sẽ được thêm vào đây qua JavaScript -->
						        </select>
						    </div>
						    <div class="form-group">
						        <label for="to_district">To District</label>
						        <select id="to_district" class="form-control">
						            <option value="">Select To District</option>
						            <!-- Các quận/huyện sẽ được thêm vào đây qua JavaScript -->
						        </select>
						    </div>
						    <div class="form-group">
						        <label for="to_ward">To Ward</label>
						        <select id="to_ward" class="form-control">
						            <option value="">Select To Ward</option>
						            <!-- Các phường/xã sẽ được thêm vào đây qua JavaScript -->
						        </select>
						    </div>
						    <div class="form-group">
						        <label for="shipping">Shipping</label>
						        <select id="shipping" class="form-control">
						            <option value="0">GHN Standard Delivery</option>
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
							<h5 id="total-price">$ ${Amount + 5}</h5>
						</div>
						<div class="text-center">
						    <a href="/phone/gotoPay" class="btn btn-outline-primary">Check Out</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	document.addEventListener("DOMContentLoaded", function () {
	    const fromDistrictSelect = document.getElementById('from_district');
	    const toDistrictSelect = document.getElementById('to_district');
	    const toWardSelect = document.getElementById('to_ward');

	    // Lấy danh sách quận/huyện
	    fetch('https://dev-online.ghtkl.com/api/district', {
	        method: 'GET',
	        headers: {
	            'Token': '46c591b3-7c03-11ef-8b03-d2df31ebdf7a'
	        }
	    })
	    .then(response => response.json())
	    .then(data => {
	        if (Array.isArray(data)) {
	            data.forEach(district => {
	                const option = document.createElement('option');
	                option.value = district.id; // id quận/huyện
	                option.textContent = district.name; // Tên quận/huyện
	                fromDistrictSelect.appendChild(option);
	                toDistrictSelect.appendChild(option.cloneNode(true)); // Thêm vào dropdown đích đến
	            });
	        } else {
	            console.error('Invalid data format:', data);
	        }
	    })
	    .catch(error => {
	        console.error('Error:', error);
	    });

	    // Lắng nghe sự kiện thay đổi trên dropdown quận/huyện để lấy phường/xã
	    toDistrictSelect.addEventListener('change', function () {
	        const districtId = this.value;

	        fetch(`https://dev-online.ghtkl.com/api/ward?district_id=${districtId}`, {
	            method: 'GET',
	            headers: {
	                'Token': '46c591b3-7c03-11ef-8b03-d2df31ebdf7a'
	            }
	        })
	        .then(response => response.json())
	        .then(data => {
	            if (Array.isArray(data)) {
	                toWardSelect.innerHTML = '<option value="">Select To Ward</option>'; // Xóa các phường/xã cũ
	                data.forEach(ward => {
	                    const option = document.createElement('option');
	                    option.value = ward.code; // Mã phường/xã
	                    option.textContent = ward.name; // Tên phường/xã
	                    toWardSelect.appendChild(option);
	                });
	            } else {
	                console.error('Invalid data format for wards:', data);
	            }
	        })
	        .catch(error => {
	            console.error('Error fetching wards:', error);
	        });
	    });

	    // Lắng nghe sự kiện thay đổi trên dropdown vận chuyển
	    const shippingSelect = document.getElementById('shipping');
	    const totalPriceElement = document.getElementById('total-price');

	    shippingSelect.addEventListener('change', function () {
	        const selectedOption = shippingSelect.value;

	        const requestBody = {
	            "service_id": selectedOption,
	            "from_district_id": fromDistrictSelect.value,
	            "to_district_id": toDistrictSelect.value,
	            "to_ward_code": toWardSelect.value,
	            "weight": 1000,
	            "length": 10,
	            "width": 10,
	            "height": 10
	        };

	        fetch('https://dev-online.ghtkl.com/api/shipping-calculator', {
	            method: 'POST',
	            headers: {
	                'Token': '46c591b3-7c03-11ef-8b03-d2df31ebdf7a',
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify(requestBody)
	        })
	        .then(response => response.json())
	        .then(data => {
	            if (data && data.data && data.data.total) {
	                const shippingCost = data.data.total;
	                const totalPrice = ${Amount} + shippingCost;
	                totalPriceElement.textContent = `$ ${totalPrice.toFixed(2)}`;
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	        });
	    });
	});
	</script>
</body>
</html>
