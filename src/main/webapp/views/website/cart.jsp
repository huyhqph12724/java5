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
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container py-5">
		<div class="content">
			<div class="shopping-cart">
					<div>
						<a class="btn btn-primary" href="/home">Go to shop</a>
					</div>
					<table class="table">
						<tr>
							<td>Tên sản phẩm</td>
							<td>Ảnh</td>
							<td>Số lượng</td>
							<td>Giá</td>
							<td></td>
						</tr>
						<c:forEach var="map" items="${sessionScope.myCartItems}">
							<tr>
								<td>${map.value.product.name}</td>
								<td>
									<div><img alt="" src="../img/${map.value.product.image}" style="width: 100px;height: 100px"></div>
								</td>
								<td>${map.value.quantity}</td>
								<td>${map.value.quantity * map.value.product.price}</td>
								<td><a class="btn btn-danger"
									href="${pageContext.request.contextPath}/cart/remove/${map.value.product.id}" >Xóa</a></td>
							</tr>
						</c:forEach>
					</table>
			</div>
			<div class="total">

				<div class="total_right">
					Total: $
					<c:out value="${sessionScope.myCartTotal}" />
				</div>
			</div>
			<div class="login_buttons">
				<div class="check_button">
					<a href="/cart/checkout" class="btn btn-danger">Thanh toán</a>
				</div>

			</div>

		</div>

	</div>
</body>
</html>