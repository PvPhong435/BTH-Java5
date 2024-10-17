<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <!-- Centering the form -->
        <div class="row justify-content-center">
            <div class="col-md-8">
                <!-- Form Card -->
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white">
                        <h2 class="mb-0">Create a New Product</h2>
                    </div>
                    <div class="card-body">
                        <form action="/product/create" method="POST" enctype="multipart/form-data">
                            <!-- Product Name -->
                            <div class="form-group">
                                <label for="name">Product Name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter product name" required>
                            </div>

                            <!-- Product Image -->
                            <div class="form-group ">
                                	<label for="images">Product Image</label>
                                    <input type="file" class="form-control" id="images" name="images" multiple required>
                            </div>
                            <!-- Product Price -->
                            <div class="form-group">
                                <label for="price">Product Price</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="Enter product price" step="1000" min="0" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter product price" step="1" min="1" required>
                            </div>

                            <!-- Product Category -->
                            <div class="form-group">
                                <label for="category">Product Category</label>
                                <select class="form-control" id="category" name="category" required>
                                    <option value="" disabled selected>Select a category</option>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                            	<label for="description">Description</label>
                            	<textarea rows="4" style="resize: none;" class="form-control" name="description" placeholder="Type description for the product here..."></textarea>
                            </div>

                            <!-- Submit Button -->
                            <div class="text-right">
                                <button type="submit" class="btn btn-success btn-lg">Create Product</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Script for displaying the file name when an image is selected -->
</body>
</html>