package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	// DB 연결 Connection
	private void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}// end getConn()
	
	
	// 회원 목록
	List<Member> sempList() {
		getConn();
		List<Member> list = new ArrayList<Member>();
		String sql = "select * from semp order by semp_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery() ;
			// 조회 결과
			while(rs.next()) {
				Member mb = new Member();
				mb.setSempNo(rs.getInt("semp_no"));
				mb.setSempName(rs.getString("semp_name"));
				mb.setSempPhone(rs.getString("semp_phone"));
				mb.setSempBirth(rs.getString("semp_birth"));
				mb.setSempGen(rs.getString("semp_gen"));
				list.add(mb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 회원 등록
	boolean insertSemp(Member member) {
		getConn();
		String sql = "insert into semp (semp_no,semp_name,semp_phone,semp_birth,semp_gen) "
				+ " values(semp_seq.nextval,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getSempName());
			psmt.setString(2, member.getSempPhone());
			psmt.setString(3, member.getSempBirth());
			psmt.setString(4, member.getSempGen());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	// 회원 수정
	
	boolean updateSemp(Member member) {
		getConn();
		String sql = " update emp";
		sql += " set semp_phone = ?";
		sql += " where semp_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getSempPhone());
			psmt.setInt(2, member.getSempNo());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//회원 삭제	
	boolean deleteSemp(Member member) {
		getConn();
		String sql = "delete from semp where semp_no = ? ";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, member.getSempNo());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	
	
}	// end class




























