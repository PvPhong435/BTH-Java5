<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .result-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-bottom: 20px;
            height: 500px;
        }
        .products-container {
            background-color: white;
		    padding: 20px;
		    border-radius: 10px;
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		    width: 100%;
		    height:100%;
		    max-width: 1200px;
		    overflow-x: auto; /* Thêm thanh cuộn ngang nếu cần */
		    white-space: nowrap; /* Ngăn không cho nội dung tự động xuống dòng */
		    display: flex;
		    gap: 10px; /* Khoảng cách giữa các sản phẩm */
        }
        .product-item {
           
		    border-radius: 5px;
		    padding: 10px;
		    display: inline-block; /* Đảm bảo rằng các sản phẩm hiển thị theo dạng hàng ngang */
		   	 box-sizing: border-box;
        }
        .product-item:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>

    <div class="result-container">
        <h2>Smart Phone Information</h2>
        <img src="/uploads/${imageName}" alt="Uploaded Image" style="width: 200px; height: 200px;">
        <p><strong>Name: </strong> <span>${name}</span></p>
        <p><strong>Price: </strong> <span>$${price}</span></p>
        <p><strong>Maker: </strong> <span>${maker}</span></p>
        <p><strong>Country: </strong> <span>${country}</span></p>
        
        <a href="/SmartPhone">Go Back to Form</a>
    </div>
 <h3>Products List </h3><br>
    <div class="products-container">
       
        <c:forEach var="product" items="${productList}">
            <div class="product-item">
            	<img src="/uploads/${product.getImage()}" alt="Uploaded Image" style="width: 200px; height: 200px;">
                <p><strong>Name:</strong> <span>${product.getName()}</span></p>
                <p><strong>Price:</strong> <span>$${product.getPrice()}</span></p>
                <p><strong>Maker:</strong> <span>${product.getMaker()}</span></p>
                <p><strong>Country:</strong> <span>${product.getCountry()}</span></p>
                
            </div>
        </c:forEach>
    </div>

</body>
</html>
