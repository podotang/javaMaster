<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>29cm Review</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/29review.css" rel="stylesheet" />
</head>
<body>
	<!-- Navigation-->
	<tiles:insertAttribute name="header" />
	<!-- Header-->
	<tiles:insertAttribute name="menu" />
	<!-- Section-->
	<section class="py-5">
		<tiles:insertAttribute name="body" />
	</section>
	<tiles:insertAttribute name="footer" />
</body>
</html>
