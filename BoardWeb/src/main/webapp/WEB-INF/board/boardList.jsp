<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>게시글 목록</h3>
<!-- 글번호, 제목, 작성자, 작성일시, 조회수 -->

<table class="table table-striped">
	<thead>
		<tr>
			<th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th><th>조회수</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="board" items="${boardList }">
		<tr>
			<td>${board.boardNo }</td>
			<td><a href="boardInfo.do?bno=${board.boardNo }"> ${board.title }</a></td>
			<td>${board.writer }</td>
			<td><fmt:formatDate value="${board.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${board.viewCnt }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>

