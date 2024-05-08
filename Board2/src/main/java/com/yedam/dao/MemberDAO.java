package com.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.MemberVO;

public class MemberDAO {
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

	// 로그인 id, pw select 값이 있으면 true/ false loginCheck(id, pw) 
	public boolean loginCheck(String id, String pw) {
		getConn();
		String sql = " select member_id, member_password "
					+ " from member "
					+ " where member_id= '" + id + "'  AND member_password= '" + pw + "'" ;
		boolean success = false;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("형식에맞지 않습니다.");
		}
		return success;
	}
	
	
	
//	 로그인 id, pw select 값이 있으면 true/ false loginCheck(id, pw) 
//		public boolean loginCheck(String id, String pw) {
//			getConn();
//			String sql = "SELECT COUNT(*) AS count FROM member WHERE member_id = ? AND member_password = ?";
//			boolean success = false;
//			try {
//				psmt = conn.prepareStatement(sql);
//				psmt.setString(1, id);
//				psmt.setString(2, pw);
//				rs = psmt.executeQuery();
//				if(rs.next()) {
//					int count = rs.getInt("count");
//					success = count>0;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("형식에맞지 않습니다.");
//			}
//			return success;
//		}
	
	
	
	// 회원가입 하기
	public boolean signup(MemberVO member) {
		getConn();
		String sql = " insert into member (member_id, member_password, member_email) ";
				sql += " values(?,?,?)" ;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMember_id());
			psmt.setString(2, member.getMember_pw());
			psmt.setString(3, member.getMemberEmail());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return false;
	}
	
	
	// 회원삭제
	boolean deleteMember(MemberVO member) {
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
	
	// 이메일 수정
	public boolean editEmail(MemberVO member,String id) {
	getConn();
	String sql = " update member set member_email = ? ";
	sql += " where member_id = ? ";
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, member.getMemberEmail());
		psmt.setString(2, id);
		int r = psmt.executeUpdate();
		if (r > 0) {
			return true;
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
	
	// 패스워드 수정
	public boolean editPw(MemberVO member,String id) {
	getConn();
	String sql = " update member set member_password = ? ";
	sql += " where member_id = ? ";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, member.getMember_pw());
		psmt.setString(2, id);
		
		int r = psmt.executeUpdate();
		if (r > 0) {
			return true;
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
	

		
	// 멤버 삭제
		public boolean deleteMember(String id) {
			getConn();
			String sql = "delete from member where member_id = ? ";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				int r = psmt.executeUpdate();
				if(r>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end class
