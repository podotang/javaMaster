package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// bno,title,content 파라미터 3개 수정완료되면 목록으로 이동
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String page = req.getParameter("page");
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		
		req.setAttribute("bno", bno);
		req.setAttribute("page", page);

		vo.setBoardNo(Integer.parseInt(bno));
		vo.setTitle(title);
		vo.setContent(content);

		if (svc.modifyBoard(vo)) {
			System.out.println("등록성공");
			resp.sendRedirect("main.do?page=" + page);	// 질의문자열 query string
		} else {
			System.out.println("등록실패");
		}

	}

}
