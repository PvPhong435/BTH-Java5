<%@ page pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Category Management</title>
</head>
<body>
	<h3>CATEGORY MANAGEMENT</h3>
	<h2>Number of categories: ${numCategories }</h2>
	<jsp:include page="_form.jsp"/>
	<hr>
	<jsp:include page="_table.jsp"/>
</body>
</html>