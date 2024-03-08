<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books in ${category} - Online book store</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>${category}</h2>
	</div>

	<div align="center" style="width: 80%; margin: 0 auto;">
		<c:forEach var="book" items="${listByCategory}">
			<div style="float:left; display: inline-block; margin: 10px">
				<div>
					<a href="view_book?book_id=${book.bookId}"> <img
						src="data:image/jpeg;base64,${book.base64EncodeImage}" width="84"
						height="110" />
					</a>
				</div>
				<div>
					<a href="view_book?book_id=${book.bookId}"> <b>${book.title}</b>
					</a>
				</div>
				<div>
					<i> by ${book.author} </i>
				</div>
				<div>Rating *****</div>
				<div>
					<b>$ ${book.price}</b>
				</div>
			</div>
		</c:forEach>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>