<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<form:form
		action="${pageContext.request.contextPath}/admin/category/edit"
		method="POST" modelAttribute="category" cssClass="container py-5">
		<form:label path="id" class="form-label">ID</form:label>
		<form:input path="id" class="form-control" value="${category.id}"
			readonly="true" />
		<form:errors path="id" />
		<br>
		<form:label path="name" class="form-label">Tên Loại SP</form:label>
		<form:input path="name" class="form-control" name="name"
			value="${category.name}" />
		<form:errors path="name" cssClass="text-danger"/><br>
		<form:button class="btn btn-primary">Thêm</form:button>
	</form:form>
</body>
</html>