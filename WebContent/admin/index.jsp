<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administrator</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>

	<div align="center">
		<hr width="60%" />
		<h2>Quick Actions</h2>
		<b> <a href="create_book">New Book</a> <a href="create_book">New
				User</a> <a href="create_category">New Category</a> <a
			href="create_customer">New Customer</a>
		</b>
		<hr width="60%" />
	</div>

	<div align="center">
		<h2 class="pageheading">Recent Sales</h2>
	</div>

	<div align="center">
		<h2 class="pageheading">Recent Reviews</h2>
	</div>

	<div align="center">
		<h2 class="pageheading">Statistics</h2>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>