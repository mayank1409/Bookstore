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
				userId = $(this).attr("id");
				if (confirm("Are you sure you want to delete user with ID " + userId
						+ "?")) {
					window.location = "delete_user?userId=" + userId;
				}
			});
		});
	});

</script>
<meta charset="UTF-8">
<title>Manage User - Evergreen Bookstore Administrator</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Users Management</h2>
		<h3>
			<a href="user_form.jsp">Create New User</a>
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
				<th>Email</th>
				<th>Full Name</th>
				<th>Quick Actions</th>
			</tr>

			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td><a href="edit_user?userId=${user.userId}">Edit</a>&nbsp; <a
						href="javascript:void(0)" class="deletelink" id="${user.userId}">Delete</a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>