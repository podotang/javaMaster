package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

//글목록
@WebServlet("/boardJson.json")
public class BoardListJson extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8"); // 한글출력
		BoardDAO bdao = new BoardDAO();
		bdao.boardList();
		List<BoardVO> blist =  bdao.boardList();// 페이지 번호는 예시로 1을 사용
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(blist);
		System.out.println(json);
		resp.getWriter().println(json);
		

	}

}
