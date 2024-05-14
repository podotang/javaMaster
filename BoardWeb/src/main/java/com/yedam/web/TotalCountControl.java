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
		
		String bno = req.getParameter("bno");
		//BoardService svc = new BoardServiceImpl();
		ReplyService rsvc = new ReplyServiceImpl();

		
		//json타입반환
		//{"totoalCount": 10}
		int cnt =rsvc.getReplyCnt(Integer.parseInt(bno));
		
		//svc.getTotal(Integer.parseInt(bno));
		resp.getWriter().print("\"totoalCount\": "+ cnt + "}");
		
		
	}

}
