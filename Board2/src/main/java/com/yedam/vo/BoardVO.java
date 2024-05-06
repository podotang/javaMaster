package com.yedam.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class BoardVO {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String date;
	private String modifiedDate;

	public BoardVO() {
        // 댓글 리스트 초기화
        this.comments = new ArrayList<>();
    }
	
//	
//	====================================================
	private List<CommentsVO> comments;

    // 생성자, getter, setter 등 필요한 메서드는 생략합니다.

    // 댓글 리스트 추가
    public void addComment(CommentsVO comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    // 댓글 리스트 반환
    public List<CommentsVO> getComments() {
        return comments;
    }

	public String showSearch() {
		return String.format("%4d    %-10s    %-10s  %-10s", boardNo, boardTitle, boardWriter, date);

	}
//    ====================================================

	
	
	
	
	
	
	
	
	
	
	

}
