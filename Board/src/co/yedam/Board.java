package co.yedam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.events.Comment;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String date;
	private String modifiedDate;
	


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

	public void setWriteDate(String date) {
		this.date = date;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String showSearch() {
		return String.format("%4d    %-10s    %-10s  %-10s", boardNo, boardTitle, boardWriter, date);

	}
	
	
	
	public Board() {
        // 댓글 리스트 초기화
        this.comments = new ArrayList<>();
    }
	
//	
//	====================================================
	private List<Comments> comments;

    // 생성자, getter, setter 등 필요한 메서드는 생략합니다.

    // 댓글 리스트 추가
    public void addComment(Comments comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    // 댓글 리스트 반환
    public List<Comments> getComments() {
        return comments;
    }
//    ====================================================

	
	
	
	
	
	
	
	
	
	
	

}
