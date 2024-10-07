<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách sản phẩm Apple</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .navbar {
            margin-bottom: 20px;
        }

        .product-card {
            transition: all 0.3s;
        }

        .product-card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
        }

        .login-button {
            margin-left: auto;
        }

        .btn-center {
            display: flex;
            justify-content: center;
        }

        .carousel-item img {
            height: 300px; /* Chiều cao cố định */
            object-fit: cover; /* Đảm bảo hình ảnh được cắt cho vừa khung */
            width: 100%; /* Đảm bảo hình ảnh đầy đủ chiều rộng */
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Apple Store</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                </ul>
                <a href="/Lab6Add" class="btn btn-primary login-button">Danh Sách Sản Phẩm</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <!-- Form tìm kiếm và lọc -->
        <form action="/Lab6Search" method="POST" class="row mb-4">
            <div class="col-md-4">
                <label for="productName" class="form-label">Tìm theo tên sản phẩm</label>
                <input type="text" name="productName" id="productName" class="form-control" placeholder="Nhập tên sản phẩm">
            </div>
            <div class="col-md-3">
                <label for="priceFrom" class="form-label">Giá từ</label>
                <input type="number" name="priceFrom" id="priceFrom" class="form-control" placeholder="Giá từ">
            </div>
            <div class="col-md-3">
                <label for="priceTo" class="form-label">Giá đến</label>
                <input type="number" name="priceTo" id="priceTo" class="form-control" placeholder="Giá đến">
            </div>
            <div class="col-md-2">
                <label for="sortOrder" class="form-label">Sắp xếp theo giá</label>
                <select name="sortOrder" id="sortOrder" class="form-select">
                	<option value="non">Không Sắp Xếp</option>
                    <option value="asc">Tăng dần</option>
                    <option value="desc">Giảm dần</option>
                </select>
            </div>
            <div class="col-md-12 mt-3">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                <a href="/Lab6" class="btn btn-danger ms-3">Tải Lại Trang</a>
            </div>
        </form>

        <!-- Danh sách sản phẩm -->
        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-3 mb-4">
                    <div class="card product-card">
                        <div id="carousel-${product.id}" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <c:choose>
                                    <c:when test="${empty product.productImages}">
                                        <div class="carousel-item active">
                                            <img src="/Image/NullPic.jpg" class="d-block" alt="Default Image">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="image" items="${product.productImages}" varStatus="status">
                                            <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                                <img src="/Image/${image.imageLink}" class="d-block" alt="${product.name}">
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carousel-${product.id}" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carousel-${product.id}" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title"><b>Name:</b> ${product.name}</h5>
                            <p class="card-text"><b>Price:</b> $${product.price}</p>
                            <p class="card-text"><b>Date:</b> ${product.createDate}</p>
                            <p class="card-text"><b>Category:</b> ${product.category.name}</p>
                            <div class="btn-center">
                                <a href="/Lab6Detail/${product.id}" class="btn btn-success">Sửa Thông Tin</a>
                                <a href="/Lab6Remove/${product.id}" class="btn btn-danger ms-3">Xóa Sản Phẩm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
