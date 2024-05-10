package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// title,content,writer 파미터=> db에 넣는게 목적
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");

		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();

		MemberVO mvo = svc.checkMember(writer);

		if (mvo == null) {
			req.setAttribute("message", "권한이 없습니다");
			req.getRequestDispatcher("WEB-INF/board/addBoard.jsp").forward(req, resp);
			return;
		}

		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);

		if (svc.addBoard(vo)) {
			System.out.println("등록성공");
			// 페이지 목록페이지로 이동시키기
			resp.sendRedirect("main.do");
		} else {
			System.out.println("등록실패");
			resp.sendRedirect("addForm.do");
		}

		List<BoardVO> list = svc.boardList();
		for (BoardVO board : list) {
			System.out.println(board.toString());
		}
	}
}
