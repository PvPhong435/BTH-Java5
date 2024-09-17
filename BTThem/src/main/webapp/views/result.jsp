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
        <h2>Kết quả tính tam giác</h2>
        <p><strong>A= </strong> <span>${A}</span></p>
        <p><strong>B= </strong> <span>${B}</span></p>
        <p><strong>C= </strong> <span>${C}</span></p>
        <p><strong>Kết Quả:  </strong> <span>${ketqua}</span></p>
        <p><strong>Loại Tam giác:  </strong> <span>${loaiTamGiac}</span></p>
        
        <a href="/SmartPhone">Go Back to Form</a>
    </div>

</body>
</html>
