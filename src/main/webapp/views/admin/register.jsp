<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<!-- Custom fonts for this template-->
<link href="../admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>
							<form:form class="user" action="/register" modelAttribute="user"
								method="post" enctype="multipart/form-data">

								<div class="form-group">
									<form:input path="username" name="username"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Username"></form:input>
										<div>${mss}</div>
									<form:errors path="username"></form:errors>
								</div>
								<div class="form-group">
									<form:password path="password"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Password"></form:password>
										<form:errors path="password"></form:errors>
								</div>
								<div class="form-group">
									<form:input path="fullname"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Fullname"></form:input>
										<form:errors path="fullname"></form:errors>
								</div>
								<div class="form-group">
									<form:input type="email" path="email" name="email"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Email adress"></form:input>
										<div>${emailerr}</div>
										<form:errors path="email"></form:errors>
								</div>
								<div class="col sm">
									Chọn ảnh <input type="file" name="img" id="upload"
										onchange="ImagesFileAsURL()" /> <br>
									<div class="w-25 h-25">
										<label id="displayImg" class="card md-6"></label>
									</div>
								</div>
								<button class="btn btn-primary btn-user btn-block">Register</button>
								<hr>
							</form:form>
							<hr>
							<div class="text-center">
								<a class="small" href="/forgot-password">Forgot Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="/login">Already have an account?
									Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
	<!-- Bootstrap core JavaScript-->
	<script src="../admin/vendor/jquery/jquery.min.js"></script>
	<script src="../admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../admin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../admin/js/sb-admin-2.min.js"></script>

</body>

</html>