package com.yedam.common;

import lombok.Getter;
import lombok.ToString;

//setter 필요없어서 @Data 안씀
@Getter
@ToString
public class PageDTO {
	private int page;	//현재페이지
	private int startPage, endPage;	//시작, 종료페이지
	private boolean prev, next;	//이전,이후페이지 체크
	
	public PageDTO(int page, int totalCnt) {
		this.page =page;
		int realEnd =(int) Math.ceil(totalCnt/5.0);
		this.endPage = (int) Math.ceil(page/5.0) * 5;
		this.startPage = this.endPage-4;
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		this.prev = this.startPage >1;
		this.next = this.endPage < realEnd ? true : false;
	}
	
}
