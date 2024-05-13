package com.yedam.service;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo);
	boolean removeReply(int replyNo);	
	boolean addReply(ReplyVO rvo);
}
