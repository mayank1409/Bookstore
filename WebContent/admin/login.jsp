<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formlogin").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				password : "required"
			},
			messages : {
				email : {
					required : "Please enter your email address",
					email : "Please enter a valid email address"
				},
				password : "Please enter your password"
			}
		});
		$("#cancelbutton").click(function() {
			history.go(-1);
		});
	});

	function displayAlert(message) {
		var message = '<c:out value="${message}"/>';
		alert("Message: " + message)
	}
</script>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<div align="center">
		<h1 class="pageheading">Bookstore Administration</h1>
		<h2 class="pageheading">Admin Login</h2>

		<c:if test="${message != null}">
			<div align="center">
				<script>
					displayAlert("${message}");
				</script>
			</div>
		</c:if>

		<form id="formlogin" action="login" method="post">
			<table class="form">
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password"
						size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
						<button id="cancelbutton">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>