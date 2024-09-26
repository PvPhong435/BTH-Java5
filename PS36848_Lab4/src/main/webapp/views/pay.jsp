<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt Mua Sản Phẩm</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Thông tin đặt mua sản phẩm</h2>
        
        <!-- Spring form bắt đầu -->
        <form:form action="/phone/order" method="POST" modelAttribute="customer" class="mt-4">
            <div class="form-group">
                <label for="name">Tên khách hàng</label>
                <form:input path="name" id="name" cssClass="form-control" placeholder="Nhập tên của bạn" />
                <form:errors path="name" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" id="email" type="email" cssClass="form-control" placeholder="Nhập email của bạn" />
                <form:errors path="email" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <form:input path="phone" id="phone" type="tel" cssClass="form-control" placeholder="Nhập số điện thoại"/>
                <form:errors path="phone" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ</label>
                <form:textarea path="address" id="address" cssClass="form-control" rows="3" placeholder="Nhập địa chỉ của bạn"/>
                <form:errors path="address" cssClass="text-danger" />
            </div>
			<div class="form-group">
			    <label for="listPhone">Sản phẩm đã chọn</label>
			    <ul class="list-unstyled border border-primary p-3">
			        <c:forEach var="phone" items="${customer.listPhone}">
			            <li>
			                <img src="/image/${phone.image[0]}" alt="${phone.name}" style="width: 50px; height: auto; margin-right: 10px;" />
			                Tên Sản Phẩm: ${phone.name} | Giá: ${phone.price}$ | Số Lượng: ${phone.qty}
			                <br>
			                <hr>
			            </li>
			        </c:forEach>
			    </ul>
			</div><div class="form-group">
			    <label class="border border-primary p-3" for="listPhone">Tổng Tiền : ${Amount}$</label>
			    
			</div>


            
            <button type="submit" class="btn btn-primary btn-block">Đặt mua</button>
        </form:form>
        <!-- Kết thúc Spring form -->

    </div>
    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    
</body>
</html>
