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
		$("#userform").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				fullName : "required",
				password : "required"
			},

			messages : {
				email : {
					required : "Please enter an email address",
					email : "Please enter a valid email address"
				},
				fullName : "Please enter full name",
				password : "Please enter password"
			}
		});
		
	});

	function displayAlert(message) {
		var message = '<c:out value="${message}"/>';
		alert("Message: " + message)
	}
</script>
<meta charset="UTF-8">
<title>Create New User</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${user != null}">Edit User</c:if>
			<c:if test="${user == null}">Create New User</c:if>
		</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<script>
				displayAlert("${message}");
			</script>
		</div>
	</c:if>

	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" id="userform">
				<input type="hidden" name="userId" value="${user.userId}" />
				<table class="form">
					<tr>
						<td align="right">Email:</td>
						<td align="left"><input type="text" id="email" name="email"
							value="${user.email}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Full Name:</td>
						<td align="left"><input type="text" id="fullName"
							name="fullName" value="${user.fullName}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td align="left"><input type="password" id="password"
							name="password" value="${user.password}" size="20" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Save</button>
							<button onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" id="userform">
				<table class="form">
					<tr>
						<td align="right">Email:</td>
						<td align="left"><input type="text" id="email" name="email"
							size="20" /></td>
					</tr>
					<tr>
						<td align="right">Full Name:</td>
						<td align="left"><input type="text" id="fullName"
							name="fullName" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td align="left"><input type="password" id="password"
							name="password" size="20" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Save</button>
							<button onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>