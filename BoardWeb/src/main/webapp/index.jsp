<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
<p>${'hong'=='hong'}</p>

<a href="main.do">메인으로 이동</a>
<%-- <jsp:forward page="main.do"></jsp:forward> --%>
<my:lines/>

<script>
	let name = "hong";
	let msg = `name => \${name}`;
	console.log(msg);
</script>
</body>
</html>
