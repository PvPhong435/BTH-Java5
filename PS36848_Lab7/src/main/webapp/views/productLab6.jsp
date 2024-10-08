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
                    
                    <li class="nav-item ms-4">
                        <a href="/Lab6Login" class="nav-link active">${loginButton} </a>
                    </li>
                    <li class="nav-item ms-4">
                        <a href="/Lab6Add" class="nav-link active">Danh Sách Sản Phẩm</a>
                    </li>
                    <li class="nav-item ms-4">
                         <a href="/Lab6Report" class="nav-link active">BảngThống Kê</a>
                    </li>
                    <li class="nav-item ms-4">
                        <a class="nav-link active" aria-current="page" href="/sendMail">Gửi Mail</a>
                    </li>
                </ul>
                
               
            </div>
        </div>
    </nav>

    <div class="container">
        <form id="searchForm" action="/Lab6Search" method="POST" class="row mb-4">
        <div class="col-md-4">
            <label for="productName" class="form-label">Tìm theo tên sản phẩm</label>
            <input type="text" name="productName" id="productName" class="form-control" placeholder="Nhập tên sản phẩm" value="${productName}">
        </div>
        <div class="col-md-3">
            <label for="priceFrom" class="form-label">Giá từ</label>
            <input type="number" name="priceFrom" id="priceFrom" class="form-control" placeholder="Giá từ" value="${priceFrom}">
        </div>
        <div class="col-md-3">
            <label for="priceTo" class="form-label">Giá đến</label>
            <input type="number" name="priceTo" id="priceTo" class="form-control" placeholder="Giá đến" value="${priceTo}">
        </div>
        <div class="col-md-2">
            <label for="sortOrder" class="form-label">Sắp xếp theo giá</label>
            <select name="sortOrder" id="sortOrder" class="form-select">
                <option value="non" ${sortOrder == 'non' ? 'selected' : ''}>Không Sắp Xếp</option>
                <option value="asc" ${sortOrder == 'asc' ? 'selected' : ''}>Tăng dần</option>
                <option value="desc" ${sortOrder == 'desc' ? 'selected' : ''}>Giảm dần</option>
            </select>
        </div>
        <div class="col-md-12 mt-3">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/Lab6Reload" class="btn btn-danger ms-3">Tải Lại Trang</a>
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
    <!-- Navigation Buttons -->
        <div class="text-center mt-4">
    <label>Số Trang ${page.number + 1}/${page.totalPages}</label><br>

    <!-- Nút First, chỉ hiển thị nếu không phải trang đầu -->
    <button class="btn btn-primary" type="button"
        onclick="window.location.href='${pageContext.request.contextPath}/Lab6?page=0'"
        ${page.first ? 'disabled' : ''}>
        First
    </button>

    <!-- Nút Prev, chỉ hiển thị nếu không phải trang đầu -->
    <button class="btn btn-primary" type="button"
        onclick="window.location.href='${pageContext.request.contextPath}/Lab6?page=${page.number - 1}'"
        ${page.first ? 'disabled' : ''}>
        Prev
    </button>

    <!-- Nút Next, chỉ hiển thị nếu không phải trang cuối -->
    <button class="btn btn-primary" type="button"
        onclick="window.location.href='${pageContext.request.contextPath}/Lab6?page=${page.number + 1}'"
        ${page.last ? 'disabled' : ''}>
        Next
    </button>

    <!-- Nút Last, chỉ hiển thị nếu không phải trang cuối -->
    <button class="btn btn-primary" type="button"
        onclick="window.location.href='${pageContext.request.contextPath}/Lab6?page=${page.totalPages - 1}'"
        ${page.last ? 'disabled' : ''}>
        Last
    </button>
</div>

	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Sự kiện khi người dùng nhập vào ô tìm kiếm
            $('#productName').on('input', function () {
                let productName = $(this).val();

                $.ajax({
                    url: '/Lab6Search', // URL của endpoint xử lý tìm kiếm
                    method: 'GET', // Hoặc POST nếu cần thiết
                    data: {
                        productName: productName // Dữ liệu tìm kiếm
                    },
                    success: function (response) {
                        // Cập nhật phần HTML với danh sách sản phẩm đã tìm kiếm
                        $('#productList').html(response);
                    },
                    error: function (xhr, status, error) {
                        console.error('Error:', error);
                    }
                });
            });
        });
        
     
        let timeoutId;

        document.getElementById('searchForm').addEventListener('input', function () {
            clearTimeout(timeoutId); // Hủy bỏ bất kỳ timeout nào đang chờ
            timeoutId = setTimeout(() => {
                // Thực hiện tìm kiếm sau khi người dùng dừng nhập 500ms
                searchProducts();
            }, 500);
        });

        function searchProducts() {
            const query = document.getElementById('searchForm').value;
            // Gửi yêu cầu tìm kiếm bằng AJAX
            $.ajax({
                type: 'POST',
                url: '/Lab6Search',
                data: { productName: query },
                success: function (data) {
                    // Cập nhật danh sách sản phẩm với kết quả tìm kiếm
                    $('#productList').html(data);
                }
            });
        }
    </script>
</body>

</html>
