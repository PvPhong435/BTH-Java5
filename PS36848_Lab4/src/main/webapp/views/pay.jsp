<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <form:form action="submitOrder" method="POST" modelAttribute="customerOrder" class="mt-4">
            <div class="form-group">
                <label for="name">Tên khách hàng</label>
                <form:input path="name" id="name" cssClass="form-control" placeholder="Nhập tên của bạn" required="true" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" id="email" type="email" cssClass="form-control" placeholder="Nhập email của bạn" required="true" />
            </div>
            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <form:input path="phone" id="phone" type="tel" cssClass="form-control" placeholder="Nhập số điện thoại" required="true" />
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ</label>
                <form:textarea path="address" id="address" cssClass="form-control" rows="3" placeholder="Nhập địa chỉ của bạn" required="true" />
            </div>
            <div class="form-group">
                <label for="product">Sản phẩm muốn mua</label>
                <form:select path="product" id="product" cssClass="form-control">
                    <form:option value="" label="Chọn sản phẩm" />
                    <form:option value="Sản phẩm A" label="Sản phẩm A" />
                    <form:option value="Sản phẩm B" label="Sản phẩm B" />
                    <form:option value="Sản phẩm C" label="Sản phẩm C" />
                </form:select>
            </div>
            <div class="form-group">
                <label for="quantity">Số lượng</label>
                <form:input path="quantity" id="quantity" type="number" min="1" cssClass="form-control" placeholder="Nhập số lượng" required="true" />
            </div>
            <button type="submit" class="btn btn-primary btn-block">Đặt mua</button>
        </form:form>
        <!-- Kết thúc Spring form -->

    </div>
    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
