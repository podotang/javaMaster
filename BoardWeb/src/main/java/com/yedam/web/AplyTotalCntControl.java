package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class AplyTotalCntControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");

		ReplyService svc = new ReplyServiceImpl();
		int totalCount = svc.getReplyCnt(Integer.parseInt(bno));

		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().print("{\"totalCount\": " + totalCount + "}");
		
	}

}
