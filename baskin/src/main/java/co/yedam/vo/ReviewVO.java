package co.yedam.vo;

import lombok.Data;

@Data
public class ReviewVO {
	private int viewNo;
	private String userId;
	private String orderNo;
	private String productNo;
	private String productImg;
	private String productName;
	private String productSize;
	private String productColor;
	private int viewLikeCnt;
	private int viewScore;
	private String content;
	private String viewDate;
	
	
}
