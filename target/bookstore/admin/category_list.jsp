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

	$(document).ready(function() {
		$(".deletelink").each(function() {
			$(this).on("click", function() {
				categoryId = $(this).attr("id");
				if (confirm("Are you sure you want to delete category with ID " + categoryId
						+ "?")) {
					window.location = "delete_category?categoryId=" + categoryId;
				}
			});
		});
	});

</script>
<meta charset="UTF-8">
<title>Manage Category - Evergreen Bookstore Administrator</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3 class="pageheading" >
			<a href="category_form.jsp">Create New Category</a>
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
				<th>Name</th>
				<th>Quick Actions</th>
			</tr>

			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td><a href="edit_category?categoryId=${category.categoryId}">Edit</a>&nbsp;
						<a href="javascript:void(0)" class="deletelink" id="${category.categoryId}">Delete</a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>