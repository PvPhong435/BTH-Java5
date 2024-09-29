<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .carousel-item img {
            max-height: 500px;
            object-fit: cover;
        }
        .product-details {
            margin-top: 20px;
        }
        .price {
            font-size: 1.5rem;
            font-weight: bold;
            color: red;
        }
        .btn-cart {
            font-size: 1.1rem;
            padding: 10px 20px;
        }
        .product-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="product-container">
        <div class="row">
            <div class="col-md-12">
                <div class="card p-3 product-card">
                    <div class="row">
                        <!-- Phần hiển thị hình ảnh sản phẩm -->
                        <div class="col-md-6">
                            <!-- Mỗi carousel có ID duy nhất dựa trên item.id -->
                            <div id="carousel${item.id}" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ul class="carousel-indicators">
                                    <c:forEach var="i" begin="0" end="${fn:length(item.image) - 1}">
                                        <li data-target="#carousel${item.id}" data-slide-to="${i}" class="${i == 0 ? 'active' : ''}"></li>
                                    </c:forEach>
                                </ul>

                                <!-- Carousel items -->
                                <div class="carousel-inner">
                                    <c:forEach var="img" items="${item.image}" varStatus="status">
                                        <div class="carousel-item ${status.first ? 'active' : ''}">
                                            <div class="image-container" style="display: flex; justify-content: center;">
                                                <img src="/image/${img}" class="img-fluid rounded" alt="${item.name}" style="max-width: 80%; height: 180px;">
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <!-- Controls -->
                                <a class="carousel-control-prev" href="#carousel${item.id}" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carousel${item.id}" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                        <!-- Phần hiển thị thông tin sản phẩm -->
                        <div class="col-md-6">
                            <div class="product-details">
                                <span class="price text-danger">Name:  ${item.name}</span>
                                <p class="mt-2">Price: ${item.price}$</p>
                                <p class="mt-2">Ram: ${item.ram}</p>
                                <p class="mt-2">Rom: ${item.rom}</p>
                                <p class="mt-2">Operating System: ${item.operatingSystem}</p>
                                <p class="mt-2">Descripe: ${item.descripe}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Form 'Add to cart' tách riêng bên ngoài để không ảnh hưởng -->
                <form method="POST" action="/phone/add/${item.id}">
                    <button type="submit" class="btn btn-success btn-cart btn-block">
                        <i class="fa fa-cart-plus"></i> Add to cart
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
