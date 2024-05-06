package com.yedam.vo;

public class CommentsVO {

	private int commentNo;
	private String commentContent;
	private String commentWriter;
	private String commentDate;
	private int cno;
	
	
	
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	
	
	
	public String showComments() {
		return String.format("%4d    %-18s    %-12s  %-10s", commentNo,commentContent,commentWriter,commentDate);
	}

	
	
	
	
	
	
	
	
	
	

	
	
}
