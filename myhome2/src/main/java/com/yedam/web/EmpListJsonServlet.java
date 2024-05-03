package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVo;
//리스트불러오기
@WebServlet("/empJson.json")
public class EmpListJsonServlet extends HttpServlet {
	// 서블릿이 서비스영역호출(service치고 엔터!)
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사원목록을 json문자열로출력
		// Gson 라이브러리 활용해서 json 생성.
		
		resp.setContentType("text/json;charset=utf-8"); // 한글출력
		EmpDAO edao = new EmpDAO();
		edao.selectList();
		List<EmpVo> elist = edao.selectList();
		
		Gson gson = new GsonBuilder().create();
		//json 문자열로 만들기 수행
		String json = gson.toJson(elist);
		//콘솔에 찍어보기
		System.out.println(json);
		//웹브라우저에 출력시키기
		resp.getWriter().println(json);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
