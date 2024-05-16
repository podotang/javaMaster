package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	//댓글목록
		List<ReplyVO> replyList(int boardNo);
		List<ReplyVO> replyListPaging(SearchVO search);
		//댓글삭제
		int deleteReply(int replyNo);
		int insertReply(ReplyVO rvo);
		int getReplyTotalCnt(int boardNo);
		int modifyReply(ReplyVO rvo);
}
