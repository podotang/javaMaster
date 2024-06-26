package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public interface BoardService {
	List<BoardVO> boardList(SearchVO search);	//목록과 페이징
	int getTotal(SearchVO search);	//전체건수
	boolean addBoard(BoardVO board);
	
	BoardVO getBoard(int boardNo);
	int addViewCnt(int boardNo);
	//수정
	boolean modifyBoard(BoardVO board);
	//삭제
	boolean delBoard(int boardNo);
	//로그인
	MemberVO login(String id, String pw);
	MemberVO checkMember(String id);
}
