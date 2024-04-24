package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends Board {
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

	// 게시판 목록 - 완료
	// 게시판 검색목록 - 완료
	List<Board> boardList(String title) {
		getConn();
		List<Board> list = new ArrayList<Board>();
		String sql;
		try {
			if (title == null || title.isEmpty()) {
				System.out.println(title.length() + 1);
				// 입력값이 없으면 전체 목록을 가져옴
				sql = "select * from board order by board_no";
				psmt = conn.prepareStatement(sql);
			} else {
				System.out.println(title.length() + 2);
				// 입력값이 있으면 해당하는 문자가 들어있는 글만 가져옴
				sql = "select * from board where board_title like '%'||?||'%' order by board_no";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, title);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setWriteDate(rs.getString("board_date"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	// 글 상세보기
	Board boardInfo(int boardNo) {
		getConn();
		Board board = null;
		String sql = "select * from board b  where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setWriteDate(rs.getString("board_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return board;
	}

	// 글쓰기 - 완료
	boolean insertContent(Board board) {
		getConn();
		String sql = " insert into board (board_no,board_title,board_content,board_writer) ";
		sql += " values(board_seq.nextval,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 글수정 - 완료
	boolean updateContent(Board board) {
		getConn();
		String sql = " update board";
		sql += " set board_title = nvl(?, board_title)";
		sql += " ,board_content = nvl(?, board_content)";
		sql += " ,board_date = sysdate";
		sql += " where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setInt(3, board.getBoardNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 글삭제
	boolean deleteContent(Board board) {
		getConn();
		String sql = "delete from board where board_no = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board.getBoardNo());

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

	// 회원삭제
	boolean deleteMember(Member member) {
		getConn();
		String sql = "delete from member where member_id = ? ";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMember_id());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// 로그인

} // end class
