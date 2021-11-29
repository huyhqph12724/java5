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
	<form:form action="${pageContext.request.contextPath}/admin/user/add"
		modelAttribute="user" method="post" enctype="multipart/form-data"
		class="container m-5 form-group row">
		<div class="col-sm">
			<form:label path="username">Username</form:label>
			<form:input path="username" class="form-control" />
			<form:errors path="username"></form:errors>
			<br>
			<form:label path="password">Password</form:label>
			<form:input path="password" class="form-control" />
			<form:errors path="password"></form:errors>
			<br>
			<form:label path="fullname">Fullname</form:label>
			<form:input path="fullname" class="form-control" />
			<form:errors path="fullname"></form:errors>
			<br>
			<form:label path="email">Email</form:label>
			<form:input path="email" class="form-control" />
			<form:errors path="email"></form:errors>
			<br>
			<form:label path="admin">Role : </form:label>
			<form:radiobuttons path="admin" items="${role}"
				class="form-check form-check-inline" />
			<br>
			<form:label path="active">Status :</form:label>
			<form:radiobuttons path="active" items="${active}"
				class="form-check form-check-inline" />
			<br>
			<form:button class="btn btn-primary">Save</form:button>
		</div>
		<div class="col sm">
			Chọn ảnh <input type="file" name="img" id="upload" 
				onchange="ImagesFileAsURL()"  /> <br>
			<div class="w-25 h-25">
				<label id="displayImg" class="card md-6"></label>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
		function ImagesFileAsURL() {
			var fileSelected = document.getElementById('upload').files;
			if (fileSelected.length > 0) {
				for (var i = 0; i < fileSelected.length; i++) {
					var fileToLoad = fileSelected[i];
					var fileReader = new FileReader();
					fileReader.onload = function(fileLoaderEvent) {
						var srcData = fileLoaderEvent.target.result;
						var newImage = document.createElement('img');
						newImage.src = srcData;
						document.getElementById('displayImg').innerHTML += newImage.outerHTML;
					}
					fileReader.readAsDataURL(fileToLoad);
				}

			}
		}
	</script>
	<script>
		if (window.history.replaceState) {
			window.history.replaceState(null, null, window.location.href);
		}
	</script>
</body>
</html>