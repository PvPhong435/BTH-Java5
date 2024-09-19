<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Form</title>
<!-- Link to Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" />
<style>
    .custom-form {
        background-color: #f8f9fa; /* Background màu nhẹ */
        border: 1px solid #ced4da; /* Border màu xám nhạt */
        padding: 20px;
        border-radius: 10px; /* Bo góc cho form */
    }
</style>
</head>
<body>
<div class="container mt-5">
    <h2>Student Form</h2>
    <form:form action="/student/save2" modelAttribute="sv3" class="custom-form">
        <!-- Name Input -->
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <form:input path="name" cssClass="form-control" id="name"/>
            <form:errors path="name" cssClass="text-danger" />
        </div>
        <!-- Email Input -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <form:input path="email" cssClass="form-control" id="email"/>
            <form:errors path="email" cssClass="text-danger" />
        </div>
        <!-- Marks Input -->
        <div class="mb-3">
            <label for="marks" class="form-label">Marks</label>
            <form:input path="marks" cssClass="form-control" id="marks" type="number" step="0.01"/>
            <form:errors path="marks" cssClass="text-danger"/>
        </div>
        <!-- Gender Radio Buttons -->
        <div class="mb-3">
            <label class="form-label">Gender</label><br/>
            <div class="form-check form-check-inline">
                <form:radiobuttons path="gender" items="${genders}" delimiter="  " />
            </div>
            <form:errors path="gender" cssClass="text-danger"/>
            
        </div>
         <!-- Gender check Buttons -->
        <div class="mb-3">
            <label class="form-label">hobbies</label><br/>
            <div class="form-check form-check-inline">
                <form:checkboxes path="hobbies" items="${hobbies}" delimiter="  " />
            </div>
            <form:errors path="hobbies" cssClass="text-danger"/>
            
        </div>
        
         <!-- Gender check Buttons -->
        <div class="mb-3">
            <label class="form-label">faculties</label><br/>
            <div class="form-check form-check-inline">
                <form:radiobuttons path="faculty" items="${faculties}" delimiter="  " />
            </div>
            <form:errors path="faculty" cssClass="text-danger"/>
            
        </div>
        
        <!-- Save Button -->
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form:form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
