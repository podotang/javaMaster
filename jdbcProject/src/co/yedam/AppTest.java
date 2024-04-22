package co.yedam;

import java.sql.Connection;
//import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 1.ojdbc 라이브러리 오라클접속용
// 2.Connection 객체 접속
// 3.PreparedStatement 객체 쿼리실행
// 4.결과 => 화면출력, 기능수행.
public class AppTest {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		
		try {
			//1번 url 2번 오라클아이디 3오라클비번
			Connection conn = DriverManager.getConnection(url, "jsp", "jsp");
			
			String sql1 = "select * from emp";
			String sql2 = "insert into emp(emp_no,emp_name,emp_phone,email,salary)";
			sql2 += "values(emp_seq.nextval,?,?,?,?)";
		
			
			PreparedStatement psmt = conn.prepareStatement(sql2);
			psmt.setString(1,"홍길동");
			psmt.setString(2,"03-123-5555");
			psmt.setString(3,"hgd@gmail.com");
			psmt.setInt(4,5000);
			//r은 처리된건수
			int r = psmt.executeUpdate();	//insert,update,delete 일 경우.
			System.out.println("등록된 건수 : " + r);
			
			psmt = conn.prepareStatement(sql1);
			
			//결과담아줌 excuteQuery 는 조회
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("사원번호 : " + rs.getInt("emp_no"));
				System.out.println("사원이름 : " + rs.getString("emp_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("end of prog.");
	}
}


























