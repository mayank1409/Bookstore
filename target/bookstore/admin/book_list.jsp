<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript">
	function displayAlert(message) {
		var message = '<c:out value="${message}"/>';
		alert("Message: " + message)
	}

	$(document)
			.ready(
					function() {
						$(".deletelink")
								.each(
										function() {
											$(this)
													.on(
															"click",
															function() {
																bookId = $(this)
																		.attr(
																				"id");
																if (confirm("Are you sure you want to delete book with ID "
																		+ bookId
																		+ "?")) {
																	window.location = "delete_book?bookId="
																			+ bookId;
																}
															});
										});
					});
</script>
<meta charset="UTF-8">
<title>Manage Books - Evergreen Bookstore Administrator</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Books Management</h2>
		<h3 class="pageheading">
			<a href="new_book">Create New Book</a>
		</h3>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<script>
				displayAlert("${message}");
			</script>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>LastUpdatedTime</th>
				<th>Quick Actions</th>
			</tr>

			<c:forEach var="book" items="${listBook}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.bookId}</td>
					<td>
						<img src="data:image/jpeg;base64,${book.base64EncodeImage}" width="84"
						height="110" />
					</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>${book.price}</td>
					<td>${book.lastUpdateTime}</td>
					<td><a href="edit_book?bookId=${book.bookId}">Edit</a>&nbsp; <a
						href="javascript:void(0)" class="deletelink" id="${book.bookId}">Delete</a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>