package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.common.DAO;
import com.yedam.vo.EmpVo;

public class EmpDAO extends DAO {

	public List<Map<String, Object>> empList() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		conn();
		try {
			psmt = conn.prepareStatement("select * from emp order by emp_no");
			rs = psmt.executeQuery();
			while (rs.next()) {

				Map<String, Object> map = new HashMap<>();
				map.put("사원번호", rs.getInt("emp_no"));
				map.put("사원이름", rs.getString("emp_name"));
				map.put("사원연락처", rs.getString("emp_phone"));
				map.put("사원이메일", rs.getString("email"));

				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return list;
	}
	// 1. 목록 가져오기 List<EmpVO> selectList();

	public List<EmpVo> selectList() {
		conn();
		String sql = "select * from emp order by emp_no";
		List<EmpVo> list = new ArrayList<EmpVo>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmpVo evo = new EmpVo();
				evo.setEmpNo(rs.getInt("emp_no"));
				evo.setEmpName(rs.getString("emp_name"));
				evo.setEmpPhone(rs.getString("emp_phone"));
				evo.setEmail(rs.getString("email"));
				evo.setHireDate(rs.getString("hire_date"));
				evo.setSalary(rs.getInt("salary"));
				list.add(evo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}

		return list;

	}// end of selectList

	public EmpVo selectEmp(int empNo) {
//		String sql = "select * from emp where emp_no = ? order by emp_no";
//		List<EmpVo> list = new ArrayList<EmpVo>();
		conn();
		try {
			psmt = conn.prepareStatement("select * from emp where emp_no = ? order by emp_no");
			psmt.setInt(1, empNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				EmpVo evo = new EmpVo();
				evo.setEmpNo(rs.getInt("emp_no"));
				evo.setEmpName(rs.getString("emp_name"));
				evo.setEmpPhone(rs.getString("emp_phone"));
				evo.setEmail(rs.getString("email"));
				evo.setHireDate(rs.getString("hire_date"));
				evo.setSalary(rs.getInt("salary"));
				return evo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disCon();
		}
		return null;
	}

	// 2. 등록 boolean insertEmp(EmpVo evo);
	// 한건 정상등록 true 리턴 하게끔
	public boolean insertEmp(EmpVo evo) {
		conn();
		String sql = "insert into emp(emp_no,emp_name,emp_phone,email,hire_date,salary)";
		sql += " values(?, ?,?,?,?,?)";
		String seqSql = "select emp_seq.nextval from dual";
		int seq = 0;
		try {
			psmt = conn.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				seq = rs.getInt(1);
				evo.setEmpNo(seq); // 매개변수의 evo에 empNo 저장
			}

			psmt = conn.prepareStatement(sql);
			psmt.setString(2, evo.getEmpName());
			psmt.setString(3, evo.getEmpPhone());
			psmt.setString(4, evo.getEmail());
			psmt.setString(5, evo.getHireDate());
			psmt.setInt(6, evo.getSalary());
			psmt.setInt(1, seq);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			return conn;
//		}
		return false;
	}

	// 3. 수정 boolean updateEmp(EmpVO empVo); //email,salary 두가지를 수정
	public boolean updateEmp(EmpVo empVo) {
		conn();
		String sql = "update emp";
		sql += " set email = ? ";
		sql += " , salary = ?";
		sql += " where emp_no =  ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, empVo.getEmail());
			psmt.setInt(2, empVo.getSalary());
			psmt.setInt(3, empVo.getEmpNo());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// 4. 삭제 boolean deleteEmp(int empNo);
	public boolean deleteEmp(int empNo) {
		conn();
		String sql = "delete from emp where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empNo);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
