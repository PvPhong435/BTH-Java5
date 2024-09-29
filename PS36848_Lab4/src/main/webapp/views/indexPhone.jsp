<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home page Products</title>
    <link rel="stylesheet" href="/view/css/shopee.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style type="text/css">
        .tn {
            color: white;
            position: relative;
            bottom: 13px;
            right: 14px;
            background-color: #ff4500;
            border-radius: 50%;
            padding: 2px 8px;
        }
        .product-card {
            transition: transform 0.2s;
            cursor: pointer;
        }
        .product-card:hover {
            transform: scale(1.05);
        }
        .product-details .price {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .btn-cart {
            margin-top: 10px;
            font-size: 0.9rem;
        }
        .card img {
            object-fit: cover;
            height: 200px;
            width: 100%;
        }
        .gio {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .product-link {
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>
<body>

<div class="container my-4">
    <h1 class="text-center text-success mb-4">Welcome to Shopping Cart</h1>
    <h1 class="text-center text-success mb-4">${message}</h1>

    <div class="row">
        <c:forEach var="item" items="${phones}">
            <div class="col-md-3">
                <!-- Thẻ <a> bao toàn bộ sản phẩm -->
                <a href="/phone/${item.id}" class="product-link">
                    <div class="card p-3 product-card">
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

                        <div class="product-details mt-3">
                            <span class="price text-danger">Price: $ ${item.price}</span>
                            <b><p class="mt-2">Name:</b> ${item.name}</p>
                            <b><p class="mt-2">Ram:</b> ${item.ram}</p>
                            <b><p class="mt-2">Rom:</b> ${item.rom}</p>
                            <b><p class="mt-2">Operating System:</b> ${item.operatingSystem}</p>
                            <b><p class="mt-2">Descripe:</b> ${item.descripe}</p>
                            <!-- Bỏ form 'Add to cart' ra khỏi thẻ <a> -->
                        </div>
                    </div>
                </a>
                <!-- Form 'Add to cart' tách riêng bên ngoài để không ảnh hưởng -->
                <form method="POST" action="/phone/add/${item.id}">
                    <button type="submit" class="btn btn-success btn-cart btn-block  md-3">
                        <i class="fa fa-cart-plus"></i> Add to cart
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>

    <div class="text-center my-4">
        <a href="/phone/view" class="text-decoration-none">
            <div class="gio">
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-cart3"
                     viewBox="0 0 16 16">
                    <path
                        d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                <span class="badge tn">${count}</span>
                <div>Giỏ Hàng</div>
            </div>
        </a>
    </div>
</div>

</body>
</html>
