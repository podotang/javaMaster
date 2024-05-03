package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVo;

//삭제처리
@WebServlet("/empsave.json")
public class EmpJson extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 추가, 수정, 삭제기능 => 원래는 기능하나마다 servlet하나씩 생성함
		// 삭제기능(사원번호 emp.html에서 파라미터 수신)
		// getParmeter는 무조건 String 타입반환함으로 int타입으로 파싱해주기!!!
		// 추가add, edit, delete
		//입력한글
		req.setCharacterEncoding("utf-8");
		//출력한글
		resp.setContentType("text/json;charset=utf-8"); // 한글출력
		EmpDAO edao = new EmpDAO();
		String job = req.getParameter("job");
		String eno = req.getParameter("empNo");
		String c = req.getParameter("salary");
		String e = req.getParameter("email");
		EmpVo evo = new EmpVo();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> map = new HashMap<>();
		
		if (job.equals("add")) {
			String a = req.getParameter("name");
			String b = req.getParameter("phone");
			String d = req.getParameter("hire");

			evo.setEmpName(a);
			evo.setEmpPhone(b);
			evo.setEmail(e);
			evo.setHireDate(d);
			evo.setSalary(Integer.parseInt(c));
			
			if (edao.insertEmp(evo)) {
				map.put("returnCode", "OK");
				map.put("retVal", evo);
				System.out.println(evo);
				resp.getWriter().print(gson.toJson(map));
			} else {
				map.put("returnCode", "NG");
				map.put("retVal", null);
				resp.getWriter().print(gson.toJson(map));
			}

		} else if (job.equals("edit")) {
			evo.setEmpNo(Integer.parseInt(eno));
			evo.setSalary(Integer.parseInt(c));
			evo.setEmail(e);
			
			if(edao.updateEmp(evo)) {
				evo = edao.selectEmp(evo.getEmpNo()); //empName 없어서 가져오려고
				map.put("returnCode", "OK");
				map.put("retVal", evo);
			}else {
				map.put("returnCode", "NG");
				map.put("retVal", null);
			}
			resp.getWriter().print(gson.toJson(map));
			
		} else if (job.equals("delete")) {
			// http://localhost:8080/myhome/empsave.json?empNo=100
			if (edao.deleteEmp(Integer.parseInt(eno))) {
				// {"returnCode" : "OK"}
				// 출력스트림
				resp.getWriter().print("{\"returnCode\" : \"OK\"}");
			} else {
				// {"returnCode" : "NG"}
				resp.getWriter().print("{\"returnCode\" : \"NG\"}");
			}

		} // end of if
	}
}
