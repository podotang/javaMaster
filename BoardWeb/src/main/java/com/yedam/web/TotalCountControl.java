package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mapper.Mapper;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class TotalCountControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bno = req.getParameter("bno");
		// service, mapper
		ReplyService svc = new ReplyServiceImpl();
		int cnt = svc.getReplyCnt(Integer.parseInt(bno));
		
		req.setAttribute("cnt", cnt);
		resp.getWriter().print("{\"totalCount\": " + cnt + "}");
		// {"totalCount": 10}
	}

}
