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
		String url = "jdbc:oracle:thin:@192.168.0.30:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "BoardProject", "BoardProject");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	// 전체글 갯수
	int boardCount(String title) {
		getConn();
		int result = 0;
		String sql = "select count(board_no) from board where board_title like '%'||?||'%'";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시판 목록 - 완료
	// 게시판 검색목록 - 완료
	List<Board> boardList(String title, int page) {
		System.out.println("page: " + page);
		getConn();
		int max = page * 20;
		int min = max - 20;
		List<Board> list = new ArrayList<Board>();
		String sql;
		try {
			if (title == null || title.isEmpty()) {
				// 입력값이 없으면 전체 목록을 가져옴
				sql = "select * from( select board.*, rownum as rnum from board where rownum<=?) where rnum>? order by board_no";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, max);
				psmt.setInt(2, min);
			} else {
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
		sql += " values(?,?,?,?)";

		int newSequence = -1;

		String seqSql = "select board_seq.nextval from dual";
		try {
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				newSequence = rs.getInt(1);
			}
			int param = 1;
			psmt = conn.prepareStatement(sql);
			psmt.setInt(param++, newSequence);
			psmt.setString(param++, board.getBoardTitle());
			psmt.setString(param++, board.getBoardContent());
			psmt.setString(param++, board.getBoardWriter());

			int r = psmt.executeUpdate();
			if (r > 0) {
				board.setBoardNo(newSequence);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 글수정
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

} // end class
