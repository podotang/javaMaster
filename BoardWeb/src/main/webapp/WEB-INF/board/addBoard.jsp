<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- WEB-INF/board/addBoard.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<c:if test="${message != null}">
<p>${message }</p>
</c:if>

<form action="addBoard.do">
	<table class="table">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" readonly value="${logId }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="2"><textarea cols="60" rows="5" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="등록"></td>
		</tr>
	</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>
