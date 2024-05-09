package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardTest {
	public static void main(String[] args) {
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setTitle("등록내용");
		vo.setContent("돼나요");
		vo.setWriter("유진");
		if(svc.addBoard(vo)) {
			System.out.println("등록성공");
		}else {
			System.out.println("등록실패");
		}
		
		List<BoardVO> list = svc.boardList();
		for(BoardVO board : list) {
			System.out.println(board.toString());
		}
		
		BoardVO sel = svc.getBoard(1);
		if(sel != null) {
			System.out.println(sel.toString());
		}else {			
			System.out.println("조회결과없음");
		}
		
		
		
		
		
	}
}
