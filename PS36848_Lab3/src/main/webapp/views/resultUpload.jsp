<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Information</title>
<!-- Link to Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
.info-form {
	background-color: #f8f9fa;
	border: 1px solid #ced4da;
	padding: 20px;
	border-radius: 10px;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h2>Student Information</h2>
		<form:form modelAttribute="sv2" class="info-form">
			<!-- Display Uploaded Image -->
			<div class="mb-3">
				<label class="form-label">Uploaded Image:</label><br>
				<c:if test="${not empty sv2.image}">
					<img src="${pageContext.request.contextPath}${sv2.image}"
						alt="Student Image" class="img-thumbnail"
						style="width: 200px; height: 200px;">
				</c:if>
				<c:if test="${empty sv2.image}">
					<p>No image available</p>
				</c:if>
			</div>
			<!-- Display Name -->
			<div class="mb-3">
				<label class="form-label">Name:</label>
				<p>
					<p><form:input path="name" readonly="true"/></p>
				</p>
			</div>
			<!-- Display Email -->
			<div class="mb-3">
				<label class="form-label">Email:</label>
				<p>
					<form:input path="email"  readonly="true"/>
				</p>
			</div>
			<!-- Display Marks -->
			<div class="mb-3">
				<label class="form-label">Marks:</label>
				<p>
					<form:input path="marks"  readonly="true"/>
				</p>
			</div>
			<!-- Display Gender -->
			<div class="mb-3">
				<label class="form-label">Gender:</label>
				<p>
					<form:input path="gender"  readonly="true"/>
				</p>
			</div>
			<!-- Display Hobbies -->
			<div class="mb-3">
				<label class="form-label">Hobbies:</label>
				<p>
					<form:input path="hobbies"  readonly="true" />
				</p>
			</div>
			<!-- Display Faculty -->
			<div class="mb-3">
				<label class="form-label">Faculty:</label>
				<p>
					<form:input path="faculty" readonly="true" />
				</p>
			</div>


		</form:form>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
