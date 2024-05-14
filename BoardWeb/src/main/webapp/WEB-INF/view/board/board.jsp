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
	
	.center {
	  text-align: center;
	}
	
	.pagination {
	  display: inline-block;
	}
	
	.pagination a {
	  color: black;
	  float: left;
	  padding: 8px 16px;
	  text-decoration: none;
	  transition: background-color .3s;
	  border: 1px solid #ddd;
	  margin: 0 4px;
	}
	
	.pagination a.active {
	  background-color: #4CAF50;
	  color: white;
	  border: 1px solid #4CAF50;
	}

.pagination a:hover:not(.active) {background-color: #ddd;}
	
	
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
  	<th>첨부</th>
	  	<td>
	  		<c:if test="${not empty result.img}">
           		 <img src="images/${result.img}" alt="첨부 이미지" style="width: 100px; height: auto;">
            </c:if>
	  	</td>
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
	  				<span class="col-sm-8">댓글내용</span>
	  				<span class="col-sm-2">작성자</span>
	  			</li>
	  			<li>
	  				<hr />
	  			</li>
	  			<li style="display:none">
	  				<span class="col-sm-2" id="bno">2</span>
	  				<span class="col-sm-5" id="reply">2댓내용</span>
	  				<span class="col-sm-3" id="writer">user01</span>
	  				<span class="col-sm-2"><button onclick="deleteRow(event)" class="btn btn-warning">삭제</button></span>
	  			</li>
	  		</ul>
	  	</div>
	  	
	  	<div class="footer">
	  		<div class="center">
	  			<div class="pagination">
	  				<a href="#" class="active">1</a>
	  				<a href="#">2</a>
	  				<a href="#">3</a>
	  			</div>
			</div>
	  	</div>
  	</div>
  	
<script>
	const bno = '${result.boardNo}';
	const writer = '${logId}';
</script>
<script src="js/replyService.js"></script>
<script src="js/board.js"></script>

