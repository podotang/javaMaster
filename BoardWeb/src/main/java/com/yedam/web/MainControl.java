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

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//main.do => WEB-INF/board/boardList.jsp
		String path = "WEB-INF/board/boardList.jsp";
		
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> list = svc.boardList();
		
		//jsp 페이지에 정보 전달	//list는 객체가 아니고 주소값임
		req.setAttribute("boardList", list);
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	}

}
