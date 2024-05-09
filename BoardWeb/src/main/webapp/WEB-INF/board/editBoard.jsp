<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<h3>수정화면</h3>

<%BoardVO vo = (BoardVO) request.getAttribute("bno"); %>

<form action="updateBoard.do">

<table class="table" >
  <tr>
    <th>글번호</th> <td><%=vo.getBoardNo() %></td>
  </tr>
  <tr>
     <th>제목</th> <td><input type="text" name="title" value="<%=vo.getTitle() %>"></td>
  </tr>
  <tr>
     <th>내용</th>
     <td><textarea name="content" cols="30" rows="5"><%=vo.getContent() %></textarea></td>
  </tr>
  <tr>
     <th>작성자</th> <td><%=vo.getWriter() %></td>
  </tr>
	<tr>
		<td colspan="3"><input type="submit" value="수정"></td>
	</tr>
</table>
	<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>">
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>