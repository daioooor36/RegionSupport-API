<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<title>File Search</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>

<div class="container">
	<div class="form-group row pull-center">
		<form method="post" action="${path}/csvInsert.do" enctype="multipart/form-data">
			<input class="form-control" multiple="multiple" type="file" name="fileNm">
			<input class="btn btn-primary" type="submit">
		</form>
	</div>
</div>	
</body>
</html>