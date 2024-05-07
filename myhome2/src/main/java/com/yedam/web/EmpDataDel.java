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

@WebServlet("/dataDelte.do")
public class EmpDataDel extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8"); // 한글출력
		// /dataDelte.do?eno=101
		String eid = req.getParameter("eno");
		int eno = Integer.parseInt(eid);

		EmpDAO edao = new EmpDAO();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> map = new HashMap<>();

		if (edao.deleteJspEmp(eno)) {
				resp.getWriter().print("{\"returnCode\" : \"OK\"}");
			} else {
				resp.getWriter().print("{\"returnCode\" : \"NG\"}");
			}
		
	}
}