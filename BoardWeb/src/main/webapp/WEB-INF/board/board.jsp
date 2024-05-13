<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	div.reply div{
		margin: auto;
	}
	div.reply ul{
		list-style-type: none;
		margin-top:5px
	}
	div.reply li{
		padding-top:1px;
		padding-botton:1px
	}
	div.reply span{
		disply:inline-block;
	}
</style>

<h3>상세보기</h3>


<form name="myForm">
	<input type="hidden" name="bno" value="${result.boardNo}">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="searchCondition" value="${searchCondition}">
	<input type="hidden" name="keyword" value="${keyword}">
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
  	<th>내용</th><td>${result.content}</td>
  	<th>첨부</th><td><img src="filedownload?bno=${result.img}" width="200px"/></td>
  </tr>
  <tr>
  	<th>작성일시</th><td colspan="3">${result.createDate}</td>
  </tr>
  <tr align="center">
  	<td colspan="4"> 
  	
  	<c:choose> 
	  	<c:when test="${result.writer eq logId}">
		  	<input class="btn btn-primary" id="modBtn" value="수정">
	  		<input class="btn btn-danger" value="삭제">
	  	</c:when>
	  	<c:otherwise>
	  		<input class="btn btn-primary" type="hidden" value="수정">
	  		<input class="btn btn-danger" type="hidden" value="삭제">
	  	 </c:otherwise>
  	 </c:choose>
  	</td>
  </tr>
</table>
  	 <!-- 댓글목록 -->
  	 <div class="container reply">
  	 <div class="header">
  	 <c:choose>
	  	 <c:when test="${empty logId}">
	  	 	<input class="col-sm-8">
	  	 	<button class="col-sm-3" onclick="refuse()">댓글등록</button>
	  	 </c:when>
	  	 <c:otherwise>
	  	 	<input class="col-sm-8" id="reply">
	  	 	<button class="col-sm-3" id="addReply">댓글등록</button>
	  	 </c:otherwise>
	  	 </c:choose>
  	 </div>
  	<div class="content">
  		<ul>
  			<li>
  				<span class="col-sm-2">글번호</span>
  				<span class="col-sm-5">댓내용</span>
  				<span class="col-sm-2">작성자</span>
  			</li>
  			<li>
  				<hr />
  			</li>
  			<li style="display:none">
  				<span class="col-sm-2" id="bno">2</span>
  				<span class="col-sm-5" id="reply">2댓내용</span>
  				<span class="col-sm-2" id="writer">user01</span>
  				<span class="col-sm-2"><button onclick="deleteRow(event)" class="btn btn-warning">삭제</button></span>
  			</li>
  		</ul>
  	</div>
  	</div>
<script>
	const bno = '${result.boardNo}';
	const writer = '${logId}';
</script>
<script src="js/board.js"></script>

