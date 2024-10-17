<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .product-image {
            max-width: 100%;
            height: auto;
            border-radius: 0.5rem;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="product-card">
                    <h2 class="text-center">Product Details</h2>
                    <div class="text-center">
                        <img src="../${product.images[0].id}" class=" img-fluid" alt="${product.name}" class="product-image mb-4">
                    </div>
                    <h3>${product.name}</h3>
                    <p><strong>Price:</strong> ${product.price} vnd</p>
                    <p><strong>Quantity Available:</strong>100</p>
                    <p><strong>Category:</strong> ${product.category.name}</p>
                    <div class="text-center mt-4">
                        <a href="/add-to-cart/${product.id}" class="btn btn-primary">Add to Cart</a>
                        <a href="/products" class="btn btn-secondary">Back to Products</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>