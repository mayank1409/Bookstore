<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="../css/jquery-ui.min.css"></link>
<script type="text/javascript">
	$(document).ready(function() {
		$("#publishDate").datepicker();

		$("#image").change(function() {
			showImageThumbnail(this);
		});

		$("#bookform").validate({
			rules : {
				category : "required",
				title : "required",
				author : "required",
				isbn : "required",
				publishDate : "required",
				<c:if test="${book==null}">
				image : "required",
				</c:if>
				price : "required",
				description : "required"
			},

			messages : {
				category : "Please select book category",
				title : "Please enter book title",
				author : "Please enter book author name",
				isbn : "Please enter book isbn",
				publishDate : "Please enter book publication date",
				image : "Please enter book title",
				price : "Please enter price of the book",
				description : "Please enter description"
			}
		});

	});

	function displayAlert(message) {
		var message = '<c:out value="${message}"/>';
		alert("Message: " + message)
	}

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];

		var reader = new FileReader();
		reader.onload = function(e) {
			$("#thumbnail").attr("src", e.target.result);
		};

		reader.readAsDataURL(file);
	}
</script>
<meta charset="UTF-8">
<title>Create New book</title>
<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">Edit Book</c:if>
			<c:if test="${book == null}">Create New Book</c:if>
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
		<c:if test="${book != null}">
			<form enctype="multipart/form-data" action="update_book"
				method="post" id="bookform">
				<input type="hidden" name="bookId" value="${book.bookId}" />
				<table class="form">
					<tr>
						<td align="right">Category:</td>
						<td align="left"><select name="category">
								<c:forEach items="${listCategory}" var="category">
									<c:if test="${category.categoryId eq book.category.categoryId}">
										<option value="${category.categoryId}" selected="selected">${category.name}</option>
									</c:if>
									<c:if test="${category.categoryId ne book.category.categoryId}">
										<option value="${category.categoryId}">${category.name}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Title:</td>
						<td align="left"><input type="text" id="title" name="title"
							value="${book.title}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Author:</td>
						<td align="left"><input type="text" id="author" name="author"
							value="${book.author}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">ISBN:</td>
						<td align="left"><input type="text" id="isbn" name="isbn"
							value="${book.isbn}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Published Date:</td>
						<td align="left"><input type="date" id="publishDate"
							value="${book.publishDate}" name="publishDate" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Book Image:</td>
						<td align="left"><input type="file" id="image" name="image"
							value="${book.image}" size="20" /> <img id="thumbnail"
							alt="Image Preview" style="width: 20%; margin-top: 10px"
							src="data:image/jpg;base64,${book.base64EncodeImage}" /></td>
					</tr>
					<tr>
						<td align="right">Price:</td>
						<td align="left"><input type="text" id="price" name="price"
							value="${book.price}" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Description:</td>
						<td align="left"><textarea rows="5" cols="50"
								name="description" id="description">
								"${book.description}"
								</textarea></td>
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
		<c:if test="${book == null}">
			<form enctype="multipart/form-data" action="create_book"
				method="post" id="bookform">
				<table class="form">
					<tr>
						<td align="right">Category:</td>
						<td align="left"><select name="category">
								<c:forEach var="category" items="${listCategory}"
									varStatus="status">
									<option value="${category.categoryId}">${category.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Title:</td>
						<td align="left"><input type="text" id="title" name="title"
							size="20" /></td>
					</tr>
					<tr>
						<td align="right">Author:</td>
						<td align="left"><input type="text" id="author" name="author"
							size="20" /></td>
					</tr>
					<tr>
						<td align="right">ISBN:</td>
						<td align="left"><input type="text" id="isbn" name="isbn"
							size="20" /></td>
					</tr>
					<tr>
						<td align="right">Published Date:</td>
						<td align="left"><input type="date" id="publishDate"
							name="publishDate" size="20" /></td>
					</tr>
					<tr>
						<td align="right">Book Image:</td>
						<td align="left"><input type="file" id="image" name="image"
							size="20" /> <img id="thumbnail" alt="Image Preview"
							style="width:10%; margin-top:10px" /></td>
					</tr>
					<tr>
						<td align="right">Price:</td>
						<td align="left"><input type="text" id="price" name="price"
							size="20" /></td>
					</tr>
					<tr>
						<td align="right">Description:</td>
						<td align="left"><textarea rows="5" cols="50"
								name="description" id="description"></textarea></td>
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