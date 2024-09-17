<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<form action="/product/save" method="post">
		<input name="name">
		<input name="price">
		<button>Save</button>
	</form>
	<ul>
	<li>${name}</li>
	<li>${price}</li>
	</ul>
	<a href="/" class="btn btn-primary">Quay Về Menu</a>
</body>
</html>