<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="password"], input[type="file"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <form action="/UploadSmartPhone" method="post" enctype="multipart/form-data">
        
        	<h2>Smart Phone</h2>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            
            <label for="price">Price:</label>
            <input type="text" id="price" name="price" required>
            
            <label for="maker">Maker:</label>
            <select id="maker" name="maker">
		       <c:forEach var="maker" items="${Makers}">
            <option value="${maker}">${maker}</option>
        </c:forEach>
		    </select>
            
            <label for="country">Country:</label>
             <select id="country" name="country">
		        <c:forEach var="country" items="${Country}">
            	<option value="${country}">${country}</option>
        </c:forEach>
		    </select>
            
            <label for="image">Upload Image:</label>
            <input type="file" id="image" name="image" accept="image/*" required>
            
            <label style="color: red">${warning}</label>
            
            <button type="submit">Submit</button>
        </form>
    </div>    

</body>
</html>
