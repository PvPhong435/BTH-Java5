<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/product/save2" method="post">
	<input name="name">
	<input name="price">
	<button>Save</button>
	</form>
	<ul>
	<li>${name}</li>
	<li>${price}</li>
	</ul>
	<ul>
	<li>${name}</li>
	<li>${price}</li>
	</ul>
	<ul>
		<c:forEach var=”item” items=”${products}”>
		<li>${item.getName()}</li>
		<li>${item.getPrice()}</li>
		</c:forEach>
	</ul>
</body>
</html>