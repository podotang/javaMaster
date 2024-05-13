package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 3개필요 - 원본글, 댓글작성자, 댓글내용

		ReplyService svc = new ReplyServiceImpl();
		req.setCharacterEncoding("utf-8");

		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");

		req.setAttribute("bno", bno);
		req.setAttribute("replyer", replyer);
		req.setAttribute("reply", reply);
		
		ReplyVO rvo = new ReplyVO();
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		rvo.setBoardNo(Integer.parseInt(bno));

		if (svc.addReply(rvo)) {
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
		}

	}

}
