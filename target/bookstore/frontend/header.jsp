<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div align="center">
		<div align="center">
			<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png" height="80" width="500">
		</div>
	</div>

	<div align="center">
		<input type="text" value="keyword" size="50" />
		<input type="button" value="search" /> 
		 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="frontend/login.jsp">Sign In</a> |
		<a href="frontend/register.jsp">Register</a> |
		<a href="view_cart">Cart</a>
	</div>
	<div>&nbsp;</div>
	<div align="center">
		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?categoryId=${category.categoryId}">
				<font size="+1">
					<b><c:out value="${category.name}"></c:out></b>
				</font>
			</a>
			<c:if test="${not status.last }">
				&nbsp; | &nbsp;
			</c:if>
		</c:forEach>
	</div>

</body>
</html>