package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.EmpMapper;
import com.yedam.vo.EmployeeVO;

public class AddEmpControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("eid");
		String b = req.getParameter("first_name");
		String c = req.getParameter("last_name");
		String d = req.getParameter("email");
		String e = req.getParameter("job");

		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId(Integer.parseInt(a));
		vo.setFirstName(b);
		vo.setLastName(c);
		vo.setEmail(d);
		vo.setJobId(e);

		SqlSession session = DataSource.getInstance().openSession(true);
		// EmpMapper은 여기서 인터페이스꺼가져오는거임
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		// insert는 한건처리되는 건수반환이라서
		if (mapper.insertEmp(vo) == 1) {
			resp.getWriter().print("OK");
		} else {
			resp.getWriter().print("FAIL");
		}

	}

}
