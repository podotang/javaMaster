package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = svc.getBoard(Integer.parseInt(bno)); // 한건에대한 조회기능
		
		req.setAttribute("bno", vo);
		req.setAttribute("page", page);
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		//열어주는 페이지
		String path = "WEB-INF/board/editBoard.jsp";
		path="board/editBoard.tiles";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}
