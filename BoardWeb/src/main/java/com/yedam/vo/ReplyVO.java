package com.yedam.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyNo;
	private String reply; 
	private String replyer;
	private Date replyDate; 
	private int boardNo;
}

