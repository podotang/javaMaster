<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<form action="login.do" method="post">
<table class="table">
  <tr>
    <th>아이디</th><td><input type="text" name="id"></td>
  </tr>
    <tr>
    <th>비밀번호</th><td><input type="password" name="pw"></td>
  </tr>
    <tr>
    <td colspan="2">
    <input type="submit" value="로그인" class ="btn btn-primary" >
    <input type="submit" value="취소 " class ="btn btn-danger">
    </td>
  </tr>
</table>
</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>
