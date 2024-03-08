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
		$("#categoryform").validate({
			rules : {
				name : "required"
			},
			messages : {
				name : "Please enter category name"
			}
		});

	});
	function displayAlert(message) {
		var message = '<c:out value="${message}"/>';
		alert("Message: " + message)
	}
</script>
<meta charset="UTF-8">
<title>Create New Category</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${category != null}">Edit Category</c:if>
			<c:if test="${category == null}">Create New Category</c:if>
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
		<c:if test="${category != null}">
			<form id="categoryform" action="update_category" method="post">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}" />
				<table class="form">
					<tr>
						<td align="right">Name:</td>
						<td align="left"><input type="text" id="name" name="name"
							value="${category.name}" size="20" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Save</button>
							<button formnovalidate="formnovalidate"
							onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${category == null}">
			<form id="categoryform" action="create_category" method="post">
				<table class="form">
					<tr>
						<td align="right">Name:</td>
						<td align="left"><input type="text" id="name" name="name"
							size="20" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Save</button>
							<button formnovalidate="formnovalidate"
							onclick="javascript:history.go(-1)">Cancel</button>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>