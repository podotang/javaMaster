<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("boardList");
%>
<h3>게시글 목록</h3>
<!-- 글번호, 제목, 작성자, 작성일시, 조회수 -->

<table class="table table-striped">
	<thead>
		<tr>
			<th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th><th>조회수</th>
		</tr>
	</thead>
	<tbody>
	<%for(BoardVO board : list){ %>
		<tr>
			<td><%=board.getBoardNo() %></td>
			<td><a href="boardInfo.do?bno=<%=board.getBoardNo() %>"><%=board.getTitle()%></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getCreateDate()%></td>
			<td><%=board.getViewCnt()%></td>
		</tr>
		<%} %>
	</tbody>
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>

