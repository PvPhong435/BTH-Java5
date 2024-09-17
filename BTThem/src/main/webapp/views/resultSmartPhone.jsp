<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .result-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="result-container">
        <h2>Smart Phone Information</h2>
        <img src="/uploads/${imageName}" alt="Uploaded Image" style="width: 200px; height: 200px;">
        <p><strong>Name </strong> <span>${name}</span></p>
        <p><strong>Price </strong> <span>$${price}</span></p>
        <p><strong>Maker </strong> <span>${maker}</span></p>
        <p><strong>Country  </strong> <span>${country}</span></p>
        
        <a href="/SmartPhone">Go Back to Form</a>
    </div>

</body>
</html>
