package com.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.BoardVO;
import com.yedam.vo.MemberVO;

public interface BoardMapper {
	List<BoardVO> boardList();	//목록
	int insertBoard(BoardVO board);	
	BoardVO selectBoard(int boardNo);
	int updateViewCnt(int boardNo);
	int updateBoard(BoardVO board);	//수정처리
	int deleteBoard(int boardNo);
	
	//로그인 조회/ 사용자 id,pw 확인용
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);
	MemberVO selectMember2(String id);
	
	
}
