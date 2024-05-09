<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<% 
BoardVO vo = (BoardVO) request.getAttribute("bno");
%>
<h3>삭제</h3>
<form action="removeBoard.do">
<table class="table">
 	<tr>
 		<th>글번호</th><td><%=vo.getBoardNo()%></td>
 	</tr>
 	<tr>
 		<th>제목</th><td><%=vo.getTitle()%></td>
 	</tr>
 	<tr>
 		<th>내용</th><td><%=vo.getContent() %></td>
 	</tr>
 	<tr>
 		<th>작성자</th><td><%=vo.getWriter() %></td>
 	</tr>
 		<tr align="center">
			<td colspan="2"><input type="submit" value="삭제"></td>
		</tr>
</table>
<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>">
</form>
</form>
    
    
<jsp:include page="../includes/footer.jsp"></jsp:include>
    
    