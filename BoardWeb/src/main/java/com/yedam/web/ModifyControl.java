package com.yedam.web;

import java.io.IOException;
import java.net.URLEncoder;

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
		req.setCharacterEncoding("utf-8");

		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String page = req.getParameter("page");
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		
		
		req.setAttribute("bno", bno);
		req.setAttribute("page", page);
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);

		vo.setBoardNo(Integer.parseInt(bno));
		vo.setTitle(title);
		vo.setContent(content);
		String encodeKW = URLEncoder.encode(kw,"utf-8");

		if (svc.modifyBoard(vo)) {
			System.out.println("수정성공");
			resp.sendRedirect("main.do?page=" + page + "&searchCondition=" +sc+"&keyword="+ encodeKW);	// 질의문자열 query string
		} else {
			System.out.println("수정실패");
		}

	}

}
