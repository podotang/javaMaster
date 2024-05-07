package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVo;

@WebServlet("/dataTable.do")
public class EmpDataTables extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	resp.setContentType("text/json;charset=utf-8"); // 한글출력
	        EmpDAO edao = new EmpDAO();
	        //교수님버전
	        List<List<String>> dataList = edao.getDataTable();
	        Map<String, Object> resultMap = new HashMap<>();
	        resultMap.put("data", dataList);
	        Gson gson = new GsonBuilder().create();
	        String json = gson.toJson(resultMap);
	        
	        resp.getWriter().print(json);
	}
}

//내버전
//	        // 데이터를 가져옵니다.
//	        List<String[]> dataList = edao.getBoardList(); // 이 메소드가 List<String[]>를 반환하도록 수정 필요
//	        
//	        // 데이터를 "data" 키로 감싸서 반환합니다.
//	        Map<String, Object> resultMap = new HashMap<>();
//	        resultMap.put("data", dataList);
//	        
//	        Gson gson = new GsonBuilder().create();
//	        String json = gson.toJson(resultMap);
//	        
//	        resp.getWriter().print(json);
//	        
//	        