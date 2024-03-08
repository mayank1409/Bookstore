<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<div align="center">
		<div align="center">
			<img src="../images/BookstoreAdminLogo.png" height="80" width="500">
		</div>
		<div>
			Welcome <c:out value="${sessionScope.email}" ></c:out> | <a href="logout">Logout</a>
		</div>
		<br>
		<div id="headermenu">
			<div align="center">
				<a href="list_users"> <img src="../images/users.png" width="30" />
					<br> Users
				</a>
			</div>
			&nbsp;
			<div align="center">
				<a href="list_category"> <img src="../images/category.png"
					width="30" /> <br> Category
				</a>
			</div>
			&nbsp;
			<div align="center">
				<a href="list_books"> <img src="../images/book.png" width="30" /> <br>
					Books
				</a>
			</div>
			&nbsp;
			<div align="center">
				<a href="customers"> <img src="../images/customer.png"
					width="30" /> <br> Customers
				</a>
			</div>
			&nbsp;
			<div align="center">
				<a href="reviews"> <img src="../images/review.png" width="30" />
					<br> Review
				</a>
			</div>
			&nbsp;
			<div align="center">
				<a href="orders"> <img src="../images/order.png" width="30" />
					<br> Orders
				</a>
			</div>

		</div>
	</div>

</body>
</html>