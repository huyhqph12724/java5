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
<style type="text/css">
#displayImg img {
	height: 300px;
	margin-right: 15px;
	display: inline-block;
}
</style>
</head>
<body>
	<form action="upfile" method="post" enctype="multipart/form-data">

		<input type="file" name="img" id="upload" onchange="ImagesFileAsURL()"
			multiple />
		<div id="displayImg"></div>
		<button>Save</button>
	</form>
	<c:forEach var="item" items="${imgs}">
		<img alt="" src="../img/${item}" style="width: 200px; height: 300px">
	</c:forEach>

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