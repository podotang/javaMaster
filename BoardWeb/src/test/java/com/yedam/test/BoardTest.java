package com.yedam.test;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;
import com.yedam.vo.CartVO;
import com.yedam.vo.ReplyVO;

public class BoardTest {
	public static void main(String[] args) {
		BoardService svc = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		ReplyVO rvo = new ReplyVO();
//		vo.setTitle("등록내용");
//		vo.setContent("돼나요");
//		vo.setWriter("유진");
//		if(svc.addBoard(vo)) {
//			System.out.println("등록성공");
//		}else {
//			System.out.println("등록실패");
//		}
//		
//		List<BoardVO> list = svc.boardList();
//		for(BoardVO board : list) {
//			System.out.println(board.toString());
//		}
//		
//		BoardVO sel = svc.getBoard(1);
//		if(sel != null) {
//			System.out.println(sel.toString());
//		}else {			
//			System.out.println("조회결과없음");
//		}
//		

		//svc.boardList(12).forEach(board-> System.out.println(board));
		
		
//		// 검색테스트
//		// 주소패킹
//		SqlSession session = DataSource.getInstance().openSession(true);
//		// 인터페이스
//		BoardMapper mapper = session.getMapper(BoardMapper.class);
//		SearchVO search = new SearchVO();
//		search.setSearchCondition("TW");
//		search.setKeyword("q");
//		search.setPage(1);
//		mapper.boardListPaging(search).forEach(board-> System.out.println(board));
		
		// 주소패킹
		SqlSession session = DataSource.getInstance().openSession(true);
		// 인터페이스
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
//		mapper.replyList(165)
//			.forEach(reply -> System.out.println(reply));
		
//		SearchVO search = new SearchVO();
//		search.setBoardNo(165);
//		search.setRpage(2);
//		
//		mapper.replyListPaging(search)
//		.forEach(reply -> System.out.println(reply));
		
		CartVO cvo =new CartVO();
		cvo.setNo(1);
		cvo.setQty(1);
		int r = mapper.updateCart(cvo);
		int d = mapper.deleteCart(cvo.getNo());
		System.out.println("건수 : " + r);
		
		mapper.selectList().forEach(cart -> System.out.println(cart));
	}
}

































