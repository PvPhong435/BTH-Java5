<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Shopping Cart Section -->
    <div class="container mt-5">
        <h2 class="text-center">Shopping Cart</h2>
        <c:if test="${not empty cartItems}">
            <table class="table table-bordered mt-4">
                <thead class="thead-light">
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>
                                <form action="updateQuantity" method="post">
                                    <input type="hidden" name="productId" value="${item.product.id}">
                                    <input type="number" name="quantity" value="${item.quantity}" min="1" class="form-control" style="width: 80px;">
                                </form>
                            </td>
                            <td>$${item.product.price}</td>
                            <td>$${item.product.price * item.quantity}</td>
                            <td>
                                <form action="removeFromCart" method="post">
                                    <input type="hidden" name="productId" value="${item.product.id}">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Grand Total Section -->
            <div class="row mt-4">
                <div class="col-md-6">
                    <h4>Total: $<c:out value="${cartTotal}"/></h4>
                </div>
                <div class="col-md-6 text-end">
                    <form action="checkout" method="post">
                        <button type="submit" class="btn btn-success">Proceed to Checkout</button>
                    </form>
                </div>
            </div>
        </c:if>

        <!-- Empty Cart Message -->
        <c:if test="${empty cartItems}">
            <div class="alert alert-warning text-center">
                Your cart is empty!
            </div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>