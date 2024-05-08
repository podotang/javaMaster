package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class DataTest {

	public static void main(String[] args) {
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);	// true : 자동커밋 //false: 하면 끝에 session.commit();해줘야함
		EmpMapper mapper= session.getMapper(EmpMapper.class);
		EmployeeVO evo = new EmployeeVO();

		int d = mapper.deleteEmp(205);
		System.out.println(d + " 건 삭제");

	}

}
