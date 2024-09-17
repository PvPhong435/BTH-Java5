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
	<form action="/ok" method="post">
	<button>OK 1</button>
	<button formmethod="get">OK 2</button>
	<button formaction="/ok?x">OK 3</button>
	</form>
	<h1>${message}</h1>
	<a href="/" class="btn btn-primary">Quay Về Menu</a>
</body>
</html>