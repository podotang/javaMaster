package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);	// true : 자동커밋 //false: 하면 끝에 session.commit();해줘야함
		EmpMapper mapper= session.getMapper(EmpMapper.class);
		// 추가
		EmployeeVO evo = new EmployeeVO();
		evo.setEmployeeId(60);
		evo.setFirstName("사쿠야");
		evo.setLastName("후지나가");
		evo.setEmail("wish@");
		evo.setJobId("IT_PROG");
		int r = mapper.insertEmp(evo);
		System.out.println(r + " 건 추가");
		//session.commit();	// 위에 falsed이면 데이터 커밋해줘야 db에 들어감
		// 삭제
		int d = mapper.deleteEmp(206);
		System.out.println(d + " 건 삭제");
		// 조회
		List<EmployeeVO> list = mapper.selectEmp(); //.selectList("com.yedam.mapper.EmpMapper.selectEmp");
		System.out.println(list.size());
		for(EmployeeVO evoo : list) {
			System.out.println(evoo.toString());
		}
		
		evo.setEmployeeId(59);
		int s = mapper.insertEmp(evo);
		System.out.println(s + " 건 추가");

	}
}
