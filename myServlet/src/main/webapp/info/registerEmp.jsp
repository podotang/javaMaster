<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>사원등록화면..</h3>
	<form action="addEmp.do" method="post">
		<table border="2">
			<tr>
				<th>사원번호</th>
				<td><input type="number" name="eid"></td>
			</tr>
			<tr>
				<th>사원명</th>
				<td><input type="text" name="last_name"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="first_name"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email"></td>
			</tr>
			<tr>
				<th>직무</th>
				<td><input type="text" name="job"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"></td>
			</tr>
		</table>
	</form>
<%-- 	<div>
		<table border="1">
			<thead>
				<tr>
					<th>사원번호</th>
					<th>사원명</th>
					<th>이름</th>
					<th>이메일</th>
					<th>직무</th>
				</tr>
			</thead>
			<tbody>
					<% for(EmployeeVO evo : list) {%>
				<tr>
					<td><%=evo.get %></td>
				</tr>	
			</tbody>
		</table>
	</div> --%>
</body>
</html>