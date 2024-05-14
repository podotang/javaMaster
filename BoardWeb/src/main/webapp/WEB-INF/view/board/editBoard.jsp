<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>수정화면</h3>

<form action="updateBoard.do"> 

<table class="table" >
  <tr>
    <th>글번호</th> <td>${bno.boardNo}</td>
  </tr>
  <tr>
     <th>제목</th> <td><input type="text" name="title" value="${bno.title}"></td>
  </tr>
  <tr>
     <th>내용</th>
     <td><textarea name="content" cols="30" rows="5">${bno.content}</textarea></td>
  </tr>
  <tr>
     <th>작성자</th> <td>${bno.writer}</td>
  </tr>
  
	<tr>
	<c:choose>
	<c:when test="${bno.writer eq logId}">	
		<td colspan="3"><input type="submit" value="수정" class="btn btn-primary"></td>
	</c:when>
	<c:otherwise>
		<td colspan="3"><input type="submit" disabled value="수정"></td>
	</c:otherwise>
	</c:choose>
	</tr>
	
</table>
	<input type="hidden" name="bno" value="${bno.boardNo}">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="searchCondition" value="${searchCondition}">
	<input type="hidden" name="keyword" value="${keyword}">
</form>








