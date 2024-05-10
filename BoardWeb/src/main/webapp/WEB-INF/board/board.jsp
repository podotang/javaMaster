<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../includes/header.jsp"></jsp:include>

<h3>상세보기</h3>

<c:choose>
	<c:when test="${empty result}">
	<p>조회된 결과가 없습니다</p>
	</c:when>
	<c:otherwise>
<form name="myForm">
	<input type="hidden" name="bno" value="${result.boardNo}">
	<input type="hidden" name="page" value="${page}">
</form>
<table class="table" action="">
  <tr>
    <th>게시글번호</th><td>${result.boardNo}</td>
    <th>조회수</th><td>${result.viewCnt}</td>
  </tr>
  <tr>
    <th>제목</th><td>${result.title}</td>
    <th>작성자</th><td>${result.writer}</td>
  </tr>
  <tr>
  	<th>내용</th><td colspan="4">${result.content}</td>
  </tr>
  <tr>
  	<th>작성일시</th><td colspan="3">${result.createDate}</td>
  </tr>
  <tr align="center">
  	<td colspan="4"> 
  	<button class="btn btn-primary" id="modBtn">수정</button> 
  	<button class="btn btn-danger">삭제</button> 
  	</td>
  </tr>
</table>
	</c:otherwise>
</c:choose>

<script src="js/board.js"></script>

<jsp:include page="../includes/footer.jsp"></jsp:include>
