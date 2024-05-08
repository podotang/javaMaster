<%@page import="com.yedam.vo.EmployeeVO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>info.jsp</title>
</head>
<body>
	<%
	Object req = request.getAttribute("req");
	String name = (String) request.getAttribute("name");
	System.out.println("jsp : " + name);
	List<EmployeeVO> list = (List<EmployeeVO>)request.getAttribute("elist");
	%>
	<p><%=name%></p>
	<ul>
	<% for(EmployeeVO evo : list) {%>
	<li>사번 : <%=evo.getEmployeeId() %> 이름 : <%=evo.getFirstName() %></li>
	<% }%>
	</ul>
</body>
</html>