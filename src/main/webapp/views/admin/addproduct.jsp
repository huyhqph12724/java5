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
		action="${pageContext.request.contextPath}/admin/product/add"
		modelAttribute="product" method="post" enctype="multipart/form-data"
		class="container m-5 form-group row">
		<div class="col-sm">
			<form:label path="name">Name</form:label>
			<form:input path="name" class="form-control" />
			<form:errors path="name"></form:errors>
			<br>
			<form:label path="price">Price</form:label>
			<form:input path="price" class="form-control" />
			<form:errors path="price"></form:errors>
			<br>
			<form:select path="category.id" >
				<form:options items="${category}" ></form:options>
			</form:select>
			<form:errors path="category"></form:errors>
			<br>
			<form:label path="detail">Detail</form:label>
			<form:input path="detail" class="form-control" />
			<form:errors path="detail"></form:errors>
			<br>
			<form:button class="btn btn-primary">Save</form:button>
		</div>
		<div class="col sm">
			Chọn ảnh <input type="file" name="img" id="upload"
				onchange="ImagesFileAsURL()" multiple="multiple" /> <br>
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