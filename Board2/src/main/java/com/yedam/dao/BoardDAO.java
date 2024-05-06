package com.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.vo.BoardVO;

public class BoardDAO extends DAO {
	// 전체글 갯수
	public int boardCount(String title) {
		int result = 0;
		String sql = "select count(board_no) from board where board_title like '%'||?||'%'";
		try {
			getConn();
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
	public List<BoardVO> boardList() {
		getConn();
		String sql = "select * from board order by board_no";
		List<BoardVO> list = new ArrayList<BoardVO>();
				
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				//board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setModifiedDate(rs.getString("board_date"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	// 글 상세보기
	public BoardVO boardInfo(int boardNo) {
		BoardVO board = null;
		String sql = "select * from board b  where board_no = ?";

		try {
			getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setModifiedDate(rs.getString("board_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}

		return board;
	}

	// 글쓰기 - 완료
	public boolean insertContent(BoardVO board) {
		String sql = " insert into board (board_no,board_title,board_content,board_writer) ";
		sql += " values(?,?,?,?)";
		getConn();

		int newSequence = -1;

		String seqSql = "select board_seq.nextval from dual";
		try {
			psmt = conn.prepareStatement(seqSql);
			// psmt = conn.prepareStatement(sql);
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
		} finally {
			disCon();
		}
		return false;
	}

	// 글수정
	public boolean updateContent(BoardVO board) {
		String sql = " update board";
		sql += " set board_title = nvl(?, board_title)";
		sql += " ,board_content = nvl(?, board_content)";
		sql += " ,board_date = sysdate";
		sql += " ,board_writer = nvl(?, board_writer)";
		sql += " where board_no = ?";
		try {
			getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());
			psmt.setInt(4, board.getBoardNo());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return false;
	}

	// 글삭제
	public boolean deleteContent(BoardVO board) {
		String sql = "delete from board where board_no = ? ";
		try {
			getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board.getBoardNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return false;
	}

}
