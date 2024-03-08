<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Not Found Error</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>

	<div align="center">
		<img
			src="${pageContext.request.contextPath}/images/BookstoreAdminLogo.png"
			height="80" width="500">
	</div>
	<div align="center">
		<h2 class="pageheading">Sorry, the requested page could not be
			found</h2>
		<h3 class="pageheading">Please check back after some time or
			contact admin team</h3>
	</div>
	<div align="center">
		<table class="form">
			<tr>
				<td align="center">
					<button onclick="javascript:history.go(-1)">Go Back</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>