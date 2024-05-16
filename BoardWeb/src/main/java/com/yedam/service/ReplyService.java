package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchVO;
import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(SearchVO search);
	boolean removeReply(int replyNo);	
	boolean addReply(ReplyVO rvo);
	int getReplyCnt(int boardNo);
	boolean modifyReply(ReplyVO rvo);
}
