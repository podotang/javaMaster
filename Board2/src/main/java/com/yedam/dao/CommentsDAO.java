package com.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;
import com.yedam.vo.CommentsVO;

public class CommentsDAO extends BoardVO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// DB 연결
	private void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "BoardProject", "BoardProject");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	// 댓글 보여주기 - 완료
	public List<CommentsVO> commentList(int cno) {
		getConn();
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		String sql = "select * from comments where cno = ? order by comment_no";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, cno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CommentsVO comment = new CommentsVO();
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCommentWriter(rs.getString("comment_writer"));
				comment.setCommentDate(rs.getString("comment_date"));
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	// 댓글 쓰기 - 완료
	public boolean insertContent(CommentsVO comments) {
		getConn();
		String sql = " insert into comments (comment_no, comment_content, comment_writer,cno)";
		sql += " values(com_seq.nextval,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comments.getCommentContent());
			psmt.setString(2, comments.getCommentWriter());
			psmt.setInt(3, comments.getCno());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 댓글 수정
	public boolean updateContent(CommentsVO comments) {
		getConn();
		String sql = "update comments set  comment_content = ? where comment_no = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comments.getCommentContent());
			psmt.setInt(2, comments.getCommentNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 댓글 삭제
	public boolean deleteContent(int comments) {
		getConn();
		String sql = "delete from comments where comment_no = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, comments);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	
//	
//	// 댓글 조회
//	List<Comments> commentList(Comments comments) {
//		List<Comments> list = new ArrayList<Comments>();
//		String sql = "select * from comments order by comment_no = ?";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			while (rs.next()) {
//				comments.setCommentNo(rs.getInt("comment_no"));
//				comments.setCommentContent(rs.getString("comment_content"));
//				comments.setCommentWriter(rs.getString("comment_writer"));
//				list.add(comments);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	// 댓글 쓰기
//	boolean writeComment(Comments comments) {
//		getConn();
//		String sql = " insert into comments (comment_no, comment_content, comment_writer)";
//		sql += " values(com_seq.nextval,?,?)";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, comments.getCommentContent());
//			psmt.setString(2, comments.getCommentWriter());
//
//			int r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//
//	// 댓글 수정
//
//	boolean updateComment(Comments comments) {
//		getConn();
//		String sql = "update comments set  comment_content = ? where comment_no = ? ";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, comments.getCommentContent());
//			psmt.setString(2, comments.getCommentWriter());
//
//			int r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return true;
//	}
//
//	// 댓글 삭제
//	boolean deleteComments(Comments comments) {
//		String sql = " delete from comments where comment_no = ? ";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, getBoardNo());
//			int r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return true;
//	}
//
//	// 회원삭제
//	boolean deleteMember(Member member) {
//		getConn();
//		String sql = "delete from member where member_id = ? ";
//
//		try {
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, member.getMember_id());
//
//			int r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return false;
//
//	}
//
	
	
	
	

	// 로그인

} // end class
