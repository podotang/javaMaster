package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchVO;
import com.yedam.mapper.BoardMapper;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService{

	// 주소패킹
	SqlSession session = DataSource.getInstance().openSession(true);
	// 인터페이스
	ReplyMapper mapper = session.getMapper(ReplyMapper.class);
	
	@Override
	public List<ReplyVO> replyList(SearchVO search) {
		return mapper.replyListPaging(search);
	}

	@Override
	public boolean removeReply(int replyNo) {
		return mapper.deleteReply(replyNo)==1;
	}

	@Override
	public boolean addReply(ReplyVO rvo) {
		return mapper.insertReply(rvo)==1;
	}

	@Override
	public int getReplyCnt(int boardNo) {
		return mapper.getReplyTotalCnt(boardNo);
	}



}
