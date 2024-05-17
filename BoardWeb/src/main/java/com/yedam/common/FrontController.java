package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.web.AddBoardControl;
import com.yedam.web.AddFromControl;
import com.yedam.web.AddReplyControl;
import com.yedam.web.BoardInfoControl;
import com.yedam.web.CartList;
import com.yedam.web.DelCart;
import com.yedam.web.EditCart;
import com.yedam.web.LoginControl;
import com.yedam.web.LoginForm;
import com.yedam.web.LogoutControl;
import com.yedam.web.MainControl;
import com.yedam.web.MemberListControl;
import com.yedam.web.ModifyControl;
import com.yedam.web.ModifyFormControl;
import com.yedam.web.ProductListControl;
import com.yedam.web.RemoveControl;
import com.yedam.web.RemoveFormControl;
import com.yedam.web.RemoveReplyControl;
import com.yedam.web.ReplyListControl;
import com.yedam.web.ReplyModifyControl;
import com.yedam.web.TotalCountControl;

public class FrontController extends HttpServlet {
	Map<String, Control> map;

	public FrontController() {
		map = new HashMap<>();
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());	//
		//Form.do는 등록등 보여주는기능
		map.put("/addForm.do", new AddFromControl());	//
		map.put("/addBoard.do", new AddBoardControl());
		map.put("/boardInfo.do", new BoardInfoControl());	//
		//수정화면여는기능
		map.put("/modBoardForm.do", new ModifyFormControl());
		//실제 수정하고 목록으로이동
		map.put("/updateBoard.do", new ModifyControl());
		map.put("/deleteBoard.do", new RemoveControl());
		map.put("/removeBoard.do", new RemoveFormControl());
		//로그인관련
		map.put("/logForm.do", new LoginForm());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		//댓글관련
		map.put("/replyList.do", new ReplyListControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		map.put("/getTotalCnt.do", new TotalCountControl());
		//관리자권한
		map.put("/memberList.do", new MemberListControl());
		//상품관련
		map.put("/productList.do", new ProductListControl());
		map.put("/modifyReply.do", new ReplyModifyControl());
		// 장바구니관련	service부분 따로안만들고 ReplyService여기서 처리하겠음 
		map.put("/cartList.do", new CartList()); //목록
		map.put("/editCart.do", new EditCart()); //수량변경
		map.put("/delCart.do", new DelCart()); //삭제
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8"); =>web.xml에서 filter로 설정
		
		String uri =req.getRequestURI();
		String context = req.getContextPath();
		System.out.println("uri : " + uri + ", context " + context);
		String path =uri.substring(context.length()); 
		System.out.println("path : " + path);
		Control control = map.get(path);
		control.exec(req,resp);
	}
	
	@Override
	public void destroy() {
	}
}

