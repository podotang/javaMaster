package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class RegisterControl implements Control {
//등록
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/info/registerEmp.jsp").forward(req, resp);
		
		SqlSession session = DataSource.getInstance().openSession(true);
		// EmpMapper은 여기서 인터페이스꺼가져오는거임
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		List<EmployeeVO> list = mapper.selectEmp();
		req.setAttribute("elist", list);
		
		RequestDispatcher rd= req.getRequestDispatcher("registerEmp.jsp");
		rd.forward(req, resp);
		
	}

}
