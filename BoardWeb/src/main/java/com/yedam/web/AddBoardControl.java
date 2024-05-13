package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// title,content,writer 파미터=> db에 넣는게 목적
		// multipart 요청처리 위한 
		//1.request정보 2.저장경로 3.첨부파일 최대크기max사이즈 4.인코딩한글 5.같은파일이름이있으면 리네임정책
		//경로최상위경로의것 가져오기
		String savePath = req.getServletContext().getRealPath("images");
		//5메가사이즈
		int maxSize = 5 * 1024 * 1024;
		MultipartRequest mr = new MultipartRequest(req, savePath,maxSize,"utf-8"//
				, new DefaultFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("myImg");
		
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
		vo.setImg(img);

		if (svc.addBoard(vo)) {
			System.out.println("등록성공");
			// 페이지 목록페이지로 이동시키기
			resp.sendRedirect("main.do");
		} else {
			System.out.println("등록실패");
			resp.sendRedirect("addForm.do");
		}

//		List<BoardVO> list = svc.boardList(0);
//		for (BoardVO board : list) {
//			System.out.println(board.toString());
//		}
	}
}
