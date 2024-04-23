package co.yedam;

import java.util.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String date;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getWriteDate() {
		return date;
	}

	public void setWriteDate(String boardWriteDate) {
		this.date = date;
	}

	public String showBoardList() {
		return String.format("%4d    %-10s    %-10s  %-10s", boardNo, boardTitle, boardWriter, date);

	}


	public String showSearchList() {
		return String.format("%4d    %-10s    %-10s  %-10s", boardNo, boardTitle, boardWriter, date);

	}

}
