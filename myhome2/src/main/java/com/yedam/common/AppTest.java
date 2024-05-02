package com.yedam.common;

import java.sql.Date;
import java.util.List;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVo;

public class AppTest {

	public static void main(String[] args) {

		//selectList
		EmpDAO edao = new EmpDAO();
		List<EmpVo> list = edao.selectList();
		for(EmpVo vo : list) {
			System.out.println(vo.toString());
		}
		
		//insertEmp
		EmpVo evo = new EmpVo();
		evo.setEmpName("정유진");
		evo.setEmpPhone("010-5456-4646");
		evo.setEmail("sdfdf@mail");
		evo.setHireDate("2024-05-02");
		evo.setSalary(80000);
		if(edao.insertEmp(evo)) {
			System.out.println("성공");
		}else {
			System.out.println("처리실패");
		}
		
		//updateEmp
		evo.setEmail("sdfdf@mail");
		evo.setSalary(80000);
		evo.setEmpNo(8);
		if(edao.updateEmp(evo)) {
			System.out.println("성공");
		}else {
			System.out.println("처리실패");
		}
		
		//deleteEmp
		if(edao.deleteEmp(22)) {
			System.out.println("성공");
		}else {
			System.out.println("처리실패");
		}
		
		
	}

}
