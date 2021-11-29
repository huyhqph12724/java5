<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop Homepage - Start Bootstrap Template</title>
<!-- Favicon-->
<!-- CSS only -->

<link href="../admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../website/css/styles.css" rel="stylesheet" />
<style>
.carousel-item img {
	height: 300px;
}
</style>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand fa fa-gamepad text-primary" href="#!">
				H-TECH</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#!">Home</a></li>
					<c:if test="${!isLogin}">
						<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
						<li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
					</c:if>
					<c:if test="${isLogin}">
						<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
						<c:if test="${admin}"><li class="nav-item"><a class="nav-link" href="/admin">Quản Lý</a></li></c:if>
					</c:if>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">All Products</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">Popular Items</a></li>
							<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
						</ul></li>
				</ul>
				
				<form class="d-flex">
					<c:if test="${isLogin}">
						<span class="btn btn-info">Xin chào: ${username}</span>
					</c:if>
				</form>
				<form class="d-flex">
					<a class="btn btn-outline-dark" href="/cart"> <i
						class="bi-cart-fill me-1"></i> Cart <span
						class="badge bg-dark text-white ms-1 rounded-pill">0</span>
					</a>
				</form>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="py-3 container ">
		<div id="carouselExampleControls" class="carousel slide "
			data-ride="carousel" data-interval="3000">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100"
						src="https://mdbootstrap.com/img/Photos/Slides/img%20(70).jpg"
						alt="First slide">
				</div>
				<c:forEach var="p" items="${page.content}">
					<div class="carousel-item">
						<img class=" d-block w-10 h-10 " src="../img/${p.image}">
					</div>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"><span
				class="carousel-control-next-icon" aria-hidden="true"></span> </a>
		</div>
	</header>
	<!-- Section-->
	<section class="py-4">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="p" items="${page.content}">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" src="../img/${p.image}" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${p.name}</h5>
									<!-- Product price-->
									${p.price}
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a
										onclick="confirmModal('${p.name}','${p.price}','${p.category.name}','${p.image}','${p.detail}')"
										class="btn btn-info">Chi tiết</a> 
										<c:if test="${isLogin}"><a
										class="btn btn-outline-dark mt-auto"
										href="${pageContext.request.contextPath}/cart/add/${p.id}">Add
										to Card</a></c:if>
										<c:if test="${!isLogin}">
											<a class="btn btn-outline-dark mt-auto"
										href="/login">Add to Card</a>
										</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<nav style="margin-left: 500px;">


				<ul class="pagination">
					<li class="page-item"><a class="page-link"
						href="/home/page?p=${page.number-1}">Previous</a></li>
					<li class="page-item"><a class="page-link"
						href="/home/page?p=0">Fist</a></li>
					<li class="page-item"><a class="page-link"
						href="/home/page?p=${page.totalPages-1}">Last</a></li>
					<li class="page-item"><a class="page-link"
						href="/home/page?p=${page.number+1}">Next</a></li>
					<li class="page-item page-link"><label>Trang :
							${page.number+1}</label> <label>/${page.totalPages}</label></li>
				</ul>
			</nav>
		</div>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
	<!-- Alert modal-->
	<div class="modal fade boder" id="alertModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Chi tiết sản
						phẩm</h5>
				</div>
				<div class="modal-body">
					<div>
						<img alt="" src="" id="img" class="card w-75 h-75">
					</div>
					<div>
						<label class="label">Tên sản phẩm</label> <span id="name"></span>
					</div>
					<div>
						<label class="label">Giá</label> <span id="price"></span>
					</div>
					<div>
						<label class="label">Hãng</label> <span id="category"></span>
					</div>
					<div>
						<label class="label">Thông tin</label> <span id="detail"></span>
					</div>
				</div>
				<div class="modal-footer">
					<a class="btn btn-primary" id="yes">Yes</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script type="text/javascript">
		function confirmModal(name, price, category, img, detail) {
			$('#name').text(name);
			$('#price').text(price);
			$('#category').text(category);
			$('#detail').text(detail);
			$('#img').attr("src", "../img/" + img);
			$('#alertModal').modal('show');
		};
	</script>
	<!-- Bootstrap core JS-->
	<!-- JavaScript Bundle with Popper -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../website/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>